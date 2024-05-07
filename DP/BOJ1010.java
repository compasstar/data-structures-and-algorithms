import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N, M;
	private static long[][] dp;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int t=0; t<T; t++) {
			run();
		}
	}
	
	private static void run() throws Exception {
		setInput();	
		System.out.println(getNumberOfBridge(M, N));
	}
	
	private static void setInput() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new long[M+1][N+1];
	}
	
	private static long getNumberOfBridge(int m, int n) {
		if (n == m || n == 0) {
			return 1;
		} 
		
		if (dp[m][n] != 0) {
			return dp[m][n];
		} 
		
		return dp[m][n] = getNumberOfBridge(m-1, n) + getNumberOfBridge(m-1, n-1);
	}

}
