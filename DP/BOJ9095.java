import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int[] dp;


	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int i=0; i<T; i++) {
			sb.append(run()).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int run() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		if (N == 1) {
			return 1;
		} else if (N == 2) {
			return 2;
		} else if (N == 3) {
			return 4;
		}
		
		
		//순서만 달라도 다른 경우의 수로 친다
		dp = new int[N+1];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		
		for (int i=0; i<N+1; i++) {
			if (i>= 3) {
				dp[i] += dp[i-3];
			}
			if (i >= 2) {
				dp[i] += dp[i-2];
			}
			
			if (i >= 1) {
				dp[i] += dp[i-1];
			}
		}
		
		return dp[N];
	}



	
}
