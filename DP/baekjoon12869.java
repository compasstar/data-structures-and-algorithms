import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 뮤탈 1개, SCV N개
 * 한 번에 3마리 공격 -> 9, 3, 1 데미지
 * SCV 체력이 주어졌을 때, 파괴하기 위해 공격해야하는 최솟값을 구하시오!
 * 
 * 2. 시간복잡도
 * 완전 탐색 -> N^(체력/데미지) = 약 3^20 = 시간초과... 하지만 최적화하면 아슬아슬하게 가능할지도?
 * 아무튼 최적의 답을 구하는 것이니까 DP 도 가능할까?
 * DP: Bottom-Up: 딱히 떠오르지 않음
 * DP: Top-Down: DFS 재귀느낌으로 하면 될 것 같은데?
 * DP로 SCV 1, 2, 3 체력 저장하고 공격횟수 저장해서 재귀로 모든 SCV 체력이 0이 될때까지 구해보자.
 * 
 * 
 * 3. 작업흐름
 * boolean[][][][] visited = new boolean[공격횟수][scv1][scv2][scv3]
 * int N
 * int[] scv 체력
 * int minAttack;
 * 
 * void recur(공격횟수, scv1, scv2, scv3)
 * if (scv1, scv2, scv3 <= 0)
 * 	minAttack, 공격횟수 비교하여 작은 값 넣기
 * 
 * if (visited == false && scv1 > 0)
 * recur(공격횟수 + 1, scv1 - 9, scv2 - 3, scv3 - 1)
 * visited[공격횟수 + 1][scv1 - 9][scv2 - 3][scv3 - 1] = true
 * 
 * recur(공격횟수 + 1, scv1 - 9, scv2 - 1, scv3 - 3)
 * ... 이렇게 6개 하기. 근데 if 문으로 체력이 0보다 클 때를 전제로 하자.
 */

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int[] scv;
	private static int minAttack;
	
	private static boolean[][][][] visited3; // 공격횟수, scv1, scv2, scv3
	private static boolean[][][] visited2;
	private static boolean[][] visited1;

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		getInput();
		
		if (N == 3) {
			recur(0, scv[0], scv[1], scv[2]);
		} else if (N == 2) {
			recur(0, scv[0], scv[1]);
		} else if (N == 1) {
			recur(0, scv[0]);
		}
		
		System.out.println(minAttack);	
	}
	
	private static void getInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		scv = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}
		minAttack = Integer.MAX_VALUE;
		
		if (N == 3) {
			visited3 = new boolean[61][61][61][61];
		} else if (N == 2) {
			visited2 = new boolean[61][61][61];
		} else if (N == 1) {
			visited1 = new boolean[61][61];
		}
	}
	
	private static void recur(int attack, int scv1, int scv2, int scv3) {
		if (scv1 == 0 && scv2 == 0 && scv3 == 0) {
			minAttack = Math.min(minAttack, attack);
			return;
		}
		
		if (visited3[attack][scv1][scv2][scv3]) {
			return;
		}
		visited3[attack][scv1][scv2][scv3] = true;
		
		if (scv1 > 0) {
			recur(attack + 1, Math.max(scv1 - 9, 0), Math.max(scv2 - 3, 0), Math.max(scv3 - 1, 0));
			recur(attack + 1, Math.max(scv1 - 9, 0), Math.max(scv2 - 1, 0), Math.max(scv3 - 3, 0));
		}
		if (scv2 > 0) {
			recur(attack + 1, Math.max(scv1 - 3, 0), Math.max(scv2 - 9, 0), Math.max(scv3 - 1, 0));
			recur(attack + 1, Math.max(scv1 - 1, 0), Math.max(scv2 - 9, 0), Math.max(scv3 - 3, 0));
		}
		if (scv3 > 0) {
			recur(attack + 1, Math.max(scv1 - 3, 0), Math.max(scv2 - 1, 0), Math.max(scv3 - 9, 0));
			recur(attack + 1, Math.max(scv1 - 1, 0), Math.max(scv2 - 3, 0), Math.max(scv3 - 9, 0));
		}	
	}
	
	private static void recur(int attack, int scv1, int scv2) {
		if (scv1 == 0 && scv2 == 0) {
			minAttack = Math.min(minAttack, attack);
			return;
		}
		
		if (visited2[attack][scv1][scv2]) {
			return;
		}
		visited2[attack][scv1][scv2] = true;
		
		if (scv1 > 0) {
			recur(attack + 1, Math.max(scv1 - 9, 0), Math.max(scv2 - 3, 0));
		}
		if (scv2 > 0) {
			recur(attack + 1, Math.max(scv1 - 3, 0), Math.max(scv2 - 9, 0));
		}
	}
	
	private static void recur(int attack, int scv1) {
		if (scv1 <= 0) {
			minAttack = Math.min(minAttack, attack);
			return;
		}
		
		if (visited1[attack][scv1]) {
			return;
		}
		visited1[attack][scv1] = true;
		
		if (scv1 > 0) {
			recur(attack + 1, Math.max(scv1 - 9, 0));
		}
	}
}
