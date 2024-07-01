
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 스티커 하나를 떼면 옆 변의 스티커는 뗄 수 없게 된다
 * 뗄 수 있는 스티커 점수의 최댓값을 구하시오!
 *
 * 2. 알고리즘 및 시간복잡도
 * N = 100,000
 * 위, 아래, 건너뛰기 중에 무엇을 선택할지는 모르겠지만
 * 아무튼 선택하여서 결국 최종적으로 최대 점수를 구하여라!
 * --> DP
 *
 * 3. 작업흐름
 *
 * int[N][3] dp;
 *
 * void 재귀함수(int index, int 업다운)
 * 만약 index == N 에 도달한다면 정지
 *
 * 만약 dp[index][업다운] 가 존재한다면 return dp[index][업다운]
 *
 * 만약 업이라면 -> return Max( 재귀함수(index + 1, 다운), 재귀함수(index + 1, 패스) )
 * 만약 다운이라면 -> return Max(재귀함수(index + 1, 업), 재귀함수(index + 1, 패스))
 * 만약 패스라면 -> return Max(재귀함수(index + 1, 업), 재귀함수(index + 1, 다운))
 *
 */

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int[] row1;
    private static int[] row2;
    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int answer = getMaxPoint();
            sb.append(answer).append('\n');
        }
        System.out.println(sb);
    }

    private static int getMaxPoint() throws Exception {
        setInput();
        int first = recur(0, 1);
        int second = recur(0, 2);
        return Math.max(first, second);
    }

    private static void setInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        row1 = new int[N];
        row2 = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            row1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            row2[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][3];
        for (int j=0; j<N; j++) {
            for (int i=0; i<3; i++) {
                dp[j][i] = -1;
            }
        }
    }

    /**
     * @param direction
     * direction 0 -> 선택안함, direction 1 -> 위, direction 2 -> 아래
     */
    private static int recur(int index, int direction) {
        if (index == N) {
            return 0;
        }

        if (dp[index][direction] != -1) {
            return dp[index][direction];
        }

        if (direction == 0) {
            return dp[index][0] = Math.max(recur(index + 1, 1), recur(index + 1, 2));
        } else if (direction == 1) {
            return dp[index][1] = Math.max(recur(index + 1, 2) + row1[index], recur(index + 1, 0) + row1[index]);
        } else if (direction == 2) {
            return dp[index][2] = Math.max(recur(index + 1, 1) + row2[index], recur(index + 1, 0) + row2[index]);
        }

        throw new RuntimeException();
    }

}
