import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 알고 있다.
 * 가장 많은 득점을 하는 타순을 찾고, 그 때의 득점을 구하시오!!
 * 1번 선수는 무조건 4번 타자임.
 * 
 * 2. 시간복잡도
 * 백트래킹으로 모든 순서를 시험해보고 가장 높은 점수를 구하면 어떨까?
 * 경우의 수 : 8!
 * 한 경기에서 점수 세기: 이닝 수 * 선수 수 = 50 * 9 = 450
 * 8! * 450 = 3 * 10^6 가능!
 * 
 * 
 * 3. 작업흐름
 * 
 * class Player(int 번호, int hit)
 * 
 * int N;
 * Player[] order : 각 선수의 순서
 * int maxPoint = 0;
 * 
 * int map = 0B0000 2진수로 왼쪽으로 밀면서 계산한다
 * 
 * 
 * 백트래킹으로 순서 바꾸기
 * dfs (int depth, int start)
 * if depth == 9 {
 * Max(maxPoint, getPoint());
 * return
 * }
 * for (i=start; i<9; i++)
 * 	만약 if depth == 3 라면, order[3] = 1번선수; dfs(depth+1, i+1); continue; 
 * 
 * 
 * 만약 i+1 == 1이라면 continue;	
 * 
 *  order[depth] = i+1
 *  dfs(depth+1, i+1)
 *  
 *  
 * 
 * 한 이닝 당 점수세기
 * private static int getPoint() {
 * 
 * for (int o : order)
 * o.hit에 따라 map을 움직이고 점수를 계산한다
 * 
 * }
 * 
 */


class Player {
	int number;
	int hit;
	
	public Player(int number, int hit) {
		this.number = number;
		this.hit = hit;
	}
}

public class Main {
	
	private static int N;
	private static int[][] hit;
	private static int[] order;
	private static boolean[] visited;
	private static int maxPoint = 0;
	private static int map;
	private static int playerNumber;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;


	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		hit = new int[N][9];
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<9; i++) {
				hit[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		order = new int[9];
		visited = new boolean[9];
		dfs(0);

		System.out.println(maxPoint);
	}
	
	private static void dfs(int depth) throws IOException {
		if (depth == 9) {
			if (order[3] != 0) {
				return;
			}
			playerNumber = 0;
			maxPoint = Math.max(maxPoint, getPoint());
			return;
		}
		
		for (int i=0; i<9; i++) {
			if (visited[i]) {
				continue;
			}
			order[depth] = i;
			visited[i] = true;
			dfs(depth + 1);
			visited[i] = false;
		}
	}
	
	private static int getPoint() throws IOException {	
		int point = 0;
		for (int inning = 0; inning < N; inning++) {
			int out = 0;
			map = 0B000;
			while (out < 3) {
				int result = hit[inning][order[playerNumber]];
				if (result == 0) {
					out++;
				} else {
					map = (map << result) + (1 << (result - 1));
					int pointMap = map >> 3;
					while (pointMap > 0) {
						if (pointMap % 2 == 1) {
							point++;
						}
						pointMap = pointMap >> 1;
					}
					map %= 8;
				}
				playerNumber = (playerNumber + 1) % 9;
			}
		}
		
		return point;
	}
}
