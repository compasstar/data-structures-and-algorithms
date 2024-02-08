/**
 * 1. 아이디어
 * 근우 -> 명우 -> 근우 -> 명우 -> ... 모든 카드 가져갈때까지 반복 가장 왼쪽 or 오른쪽 카드 한 장 가져간다
 * 점수 = 카드에 적힌 수의 합
 * 두 플레이어는 둘 다 최선의 선택을 한다.
 * 근우가 얻을 수 있는 점수를 구하시오!
 * 
 * 결과적으로 최선의 결과가 나온다 --> DP
 * 왼쪽 오른쪽 고르는 경우를 모두 고려한다 --> 백트래킹(DFS) --> 시간복잡도 카드 1000개니까 2^1000 안됨...
 * 
 * DP로 풀어야 하는데, 가능한가??
 * 가장 왼쪽 + 나머지 오른쪽 점수 합
 * 가장 오른쪽 + 나머지 왼쪽 점수 합
 * 재귀로 돌면서 둘 중 더 큰쪽을 선택하며 최선의 전략을 취한다
 * 근우의 턴에는 점수를 더해준다

 * 
 * 2. 시간복잡도
 * 이진트리로 1 ~ N 개까지를 도는 느낌이니까, 1 + 2 + 4 + 8 + ... + N --> O(N^2)
 * T만큼 게임을 진행하니까 T * N^2 = 50 * 10*6 음 아슬아슬하게 될 것 같은데?
 * 
 * 3. 작업흐름
 * int T, int N
 * int totalPoint
 * 
 * 재귀함수로 더 큰쪽으로 계속 리턴한다
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;




public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int[] cards;
	private static int[][] dp;
	

	public static void main(String[] args) throws IOException  {
		int T = Integer.parseInt(br.readLine());
		for (int i=0; i<T; i++) {
			run();
		}
		System.out.println(sb);
	}
	
	public static void run() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		cards = new int[N];
		dp = new int[N+1][N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		recur(0, N-1, true);
		sb.append(dp[0][N-1]).append('\n');
		
//		sb.append(recur2(0, N-1, true)).append('\n');
	}
	
	private static int recur(int left, int right, boolean turn) {
		
		if (turn && left == right) {
			return dp[left][right] = cards[left];
		} else if (left == right) {
			return 0;
		}
		
		if (dp[left][right] != 0) {
			return dp[left][right];
		}
		
		if (turn) {
			return dp[left][right] = Math.max(cards[left] + recur(left + 1, right, !turn), cards[right] + recur(left, right - 1, !turn));
		}
		return dp[left][right] = Math.min(recur(left+1, right, !turn), recur(left, right - 1, !turn));
	}

	
	
	private static int recur2(int left, int right, boolean turn) {
		if (left > right) {
			return 0;
		}
		if (turn && left == right) {
			return cards[left];
		} else if (left == right) {
			return 0;
		}
		
		if (turn) {
			return  Math.max(cards[left] + recur2(left + 1, right, !turn), cards[right] + recur2(left, right - 1, !turn));
		}
		return Math.min(recur2(left+1, right, !turn), recur2(left, right - 1, !turn));
	}

}
