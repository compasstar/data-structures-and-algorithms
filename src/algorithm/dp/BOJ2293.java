package algorithm.dp;
/**
 * 1. 아이디어
 * 동전 n 가지 종류
 * 가치의 합이 k 가 되어야 한다
 * 동전은 마음껏 사용해도 된다
 * 모든 경우의 수 구하기 
 * 
 * 2. 알고리즘 선택
 * 동전의 종류 n = 100
 * 총 가치 k = 10,000
 * 동전의 가치 =< 100,000
 * 
 * DFS: 1원 10000개, 2월 9998개, ... 너무 경우의 수 많다
 * 그리디: 큰것부터 음... 안됨
 * DP: Bottom-Up 으로 해당 값이 도달할 때에 List 로 경우의 수 계속해서 추가하면 될 것 같은데...?
 * 
 * 3. 작업흐름
 * 
 * int[100,001] coins
 * 
 * 동전 가치에 일단 전부 1을 넣는다
 * 예) coin[1] = 1, coin[2] = 1, coin[5] = 1
 * for 문으로 2 ~ 동전가치까지
 * if (coin[i-1] > 0) coins[i]++
 * if (coin[i-2] > 0) coins[i]++ 
 * 이런식으로 쭉쭉쭉하고
 * 
 * coins[K] 값을 출력하면 된다
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class BOJ2293 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int K;
	private static int[] coin;
	private static int[] dp;

	public static void main(String[] args) throws IOException  {

		getInput();
		
		for (int c : coin) {
			dp[c]++;
			for (int i = c + 1; i <= K; i++) {
				dp[i] += dp[i - c];
			}
		}

		
		System.out.println(dp[K]);
	}
	
	private static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coin = new int[N];
		for (int i=0; i<N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[100001];
	}


}
