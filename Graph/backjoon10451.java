import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 순열 사이클의 개수를 구하시오
 * 순열을 그래프로 만든다
 * 그래프를 bfs로 돌면서 check한다
 * 다 돌아서 사이클의 개수를 세면 완료!
 *
 * 2. 시간복잡도
 * 그래프 한 번 돌기: N
 * BFS 탐색: N^2
 * O(N^2) = 10^6 < 2억
 *
 * 3. 작업흐름
 * int[][] graph = new int[N+1][N+1] (노드가 1부터 시작하므로 인덱스0은 비워둔다)
 * 그래프에 순열을 넣는다
 * int[N+1] check 방문여부. 0이면 방문x, 1이면 방문o
 * for (int i=1; i<N+1; i++)
 * 만약 check == 1이라면 continue;
 * check == 0 이라면 check를 1로 바꾸로 bfs메서드 실행 -> 카운트 + 1
 *
 * 메서드 bfs(노드): 그래프[노드]를 돌면서 해당되는 노드들 check[N] = 1
 *
 * 정답 = 카운트
 */


public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[][] numbers;
    private static int[] check;
    private static int count;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case < T+1; test_case++) {
            System.out.println(getCycleNumber());
        }
    }

    private static int getCycleNumber() throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        numbers = new int[N+1][N+1];
        check = new int[N+1];
        count = 0;

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<N+1; i++) {
            int thisNumber = Integer.parseInt(st.nextToken());
            numbers[i][thisNumber] = 1;
            numbers[thisNumber][i] = 1;
        }

        for (int i=1; i<N+1; i++) {
            if (check[i] == 1) {
                continue;
            }
            check[i] = 1;
            bfs(i);
            count++;
        }

        return count;
    }

    private static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Integer nowNode = queue.remove();

            for (int i=1; i<N+1; i++) {
                if (numbers[nowNode][i] == 1 && check[i] == 0) {
                    queue.add(i);
                    check[i] = 1;
                }
            }
        }
    }
}
