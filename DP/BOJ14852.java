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
		
		if (N == 1) {
			System.out.println(2);
			return;
		} else if (N == 2) {
			System.out.println(7);
			return;
		}
		
		int[] dp = new int[N+1];
		int[] sum = new int[N+1];
		
		dp[0] = 1;
		dp[1] = 2;
		dp[2] = 7;
		
		sum[0] = 1;
		sum[1] = sum[0] + dp[1];
		sum[2] = sum[1] + dp[2];

		for (int i=2; i<N+1; i++) {
			if (i <= 2) {
				continue;
			}
			
 			dp[i] = (dp[i-2]+ (2* sum[i-1]) % 1000000007) % 1000000007;
			sum[i] = (sum[i-1] + dp[i]) % 1000000007;
 			
		}
		System.out.println(dp[N]);
	}
}
