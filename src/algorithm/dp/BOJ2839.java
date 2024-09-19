package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2839 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;


	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		dp[3] = 1;
		
		if (N >= 5) {
			dp[5] = 1;
		}

		for (int i=0; i<N+1; i++) {
			if (i < 5) {
				continue;
			}
			
			if (dp[i-5] == 0 && dp[i-3] != 0) {
				dp[i] = dp[i-3] + 1;
			} else if (dp[i-5] != 0 && dp[i-3] == 0) {
				dp[i] = dp[i-5] + 1;
			} else if (dp[i-5] != 0 && dp[i-3] != 0) {
				dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
			}

		}
		if (dp[N] == 0) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(dp[N]);
	}



	
}
