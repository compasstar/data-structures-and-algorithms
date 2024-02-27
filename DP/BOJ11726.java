import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;


	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];

		dp[1] = 1;
		if (N >= 2) {
			dp[2] = 2;
		}

		for (int i=2; i<N+1; i++) {
			if (i <= 2) {
				continue;
			}
			
 			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		
		System.out.println(dp[N]);
	}



	
}
