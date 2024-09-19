package algorithm.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 공기청정기 -> 무조건 1번 열, 두 칸 차지

 * [미세먼지 확산]
 * 인접한 네 방향으로 확산된다. (공기청정기 있으면 제외)
 * 확산되는 양은 A/5 이다. (소수점 버림)
 * 남은 미세전지의 양은 (A - (A/5) * 확산된 방향 개수) 이다. 즉 양은 보존 된다.

 * [공기청정기 작동]
 * 위 공기청정기: 반시계 방향으로 순환
 * 아래 공기청정기: 시계 방향으로 순환
 * 결국 공기청정기로 들어간 미세먼지는 사라진다.

 * T초 후에 방에 남아있는 미세먼지의 양을 구하시오!


 * 2. 알고리즘 및 시간복잡도
 * 구현 문제로, 미세먼지 확산 함수와 공기청정기 작동 함수 두개를 만든다
 * T초 동안 반복문 마다 미세먼지 확산 -> 공기청정기 작동을 실행한다.

 * [시간복잡도]
 * 한번 실행할때 맵의 크기를 한번 돈다. R*C = 2500
 * T초 동안 실행하므로 T * R * C = 2500 * 1000 = 2,500,000
 * 1초 당 맵을 한번만 도는건 아니므로 조금 더 크겠지만 시간은 어쨌든 안정권이다.


 * 3. 작업흐름
 * for (int i=0; i<T; i++) {
 * 미세먼지확산
 * 공기청정기작동
 * }
 * int answer = 미세먼지개수세기

 * void 미세먼지확산
 * 2중 for문으로 맵을 돌면서 확산을 모두 실행시킨다. (만약 칸이 0이라면 pass)
 * 1) 확산 가능한 방향 개수와 어느 방향인지 구한다
 * 2) 가능한 방향에 대하여 원래 있던 값에 A/5 값을 더해준다.
 * 3) 원래 있던 칸에 대하여 떠난 값들을 빼준다.


 * void 공기청정기작동
 * 위공기청정기작동:
 * 1열에서 한칸씩 내린다. 만약 공기청정기 바로 윗 칸이라면 제거
 * 1행을 왼쪽으로 한칸씩 민다. 만약 가장 왼쪽 칸이었다면 밑으로 한칸
 * 끝열을 한칸씩 올린다. 만약 가장 윗 칸이었다면 왼쪽으로 한칸
 * 공기청정기와 같은 행을 오른쪽으로 민다. 만약 가장 오른쪽 칸이었다면 위로 한 칸

 * 아래공기청정기 작동: 위와 반대로 작동시킨다.


 * int 미세먼지개수세기:
 * 2중 for문으로 맵을 돌면서 미세먼지 개수를 구하려 리턴한다.
 */

public class BOJ17144 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int R, C, T;
    private static int[][] map;
    private static int[][] copyMap;
    private static int[] cleaner;

    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        setInput();
        for (int t = 0; t < T; t++) {
            spreadDust();
            cleanDust();
        }
        int answer = countDust();
        System.out.println(answer);
    }

    private static void setInput() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        cleaner = new int[2];
        int cleanerIndex = 0;

        for (int j = 0; j < R; j++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < C; i++) {
                map[j][i] = Integer.parseInt(st.nextToken());
                if (map[j][i] == -1) {
                    cleaner[cleanerIndex++] = j;
                }
            }
        }
    }

    private static void spreadDust() {
        copyMap = new int[R][C];
        for (int j=0; j<R; j++) {
            for (int i=0; i<C; i++) {
                if (map[j][i] == 0 || map[j][i] == -1) {
                    continue;
                }
                spread(j, i);
            }
        }

        for (int j=0; j<R; j++) {
            for (int i=0; i<C; i++) {
                map[j][i] += copyMap[j][i];
            }
        }
    }

    private static void spread(int y, int x) {
        boolean[] isSpread = new boolean[4];
        int spreadCount = 0;
        for (int d=0; d<4; d++) {
            int nextY = y + dy[d];
            int nextX = x + dx[d];
            if (nextY < 0 || nextY >= R || nextX < 0 || nextX >= C) {
                continue;
            }
            if (map[nextY][nextX] == -1) {
                continue;
            }
            isSpread[d] = true;
            spreadCount++;
        }

        for (int d=0; d<4; d++) {
            if (!isSpread[d]) {
                continue;
            }
            int nextY = y + dy[d];
            int nextX = x + dx[d];
            copyMap[nextY][nextX] += map[y][x] / 5;
        }

        copyMap[y][x] -= (map[y][x] / 5) * spreadCount;
    }

    private static void cleanDust() {
        upClean(cleaner[0]);
        downClean(cleaner[1]);
    }

    private static void upClean(int y) {
        //1열 청소
        for (int j = y-1; j > 0; j--) {
            map[j][0] = map[j-1][0];
        }

        //1행 청소
        if (y != 0) {
            for (int i = 0; i < C-1; i++) {
                map[0][i] = map[0][i+1];
            }
        }

        //끝열 청소
        for (int j=0; j<y; j++) {
            map[j][C-1] = map[j+1][C-1];
        }

        //끝행 청소
        for (int i=C-1; i>1; i--) {
            map[y][i] = map[y][i-1];
        }
        map[y][1] = 0;
    }

    /**
     * 시계방향으로 돈다
     */
    private static void downClean(int y) {
        //1열 청소
        for (int j = y+1; j<R-1; j++) {
            map[j][0] = map[j+1][0];
        }

        //끝행 청소
        if (y != R-1) {
            for (int i=0; i<C-1; i++) {
                map[R-1][i] = map[R-1][i+1];
            }
        }

        //끝열 청소
        for (int j=R-1; j>y; j--) {
            map[j][C-1] = map[j-1][C-1];
        }

        //y행 청소
        for (int i=C-1; i>1; i--) {
            map[y][i] = map[y][i-1];
        }
        map[y][1] = 0;
    }

    private static int countDust() {
        int count = 0;
        for (int j=0; j<R; j++) {
            for (int i=0; i<C; i++) {
                count += map[j][i];
            }
        }
        return count + 2;
    }
}
