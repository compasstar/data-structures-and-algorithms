import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int cnt0;
	private static int cnt1;
	private static int[] dp0;
	private static int[] dp1;


	public static void main(String[] args) throws NumberFormatException, IOException {

		int T = Integer.parseInt(br.readLine());
		for (int i=0; i<T; i++) {
			run();
		}
		
		System.out.println(sb);
	}

	private static void run() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		cnt0 = 0;
		cnt1 = 0;
		
		dp0 = new int[N+1];
		dp1 = new int[N+1];
		
		
		
		sb.append(fibonacchi0(N)).append(' ').append(fibonacchi1(N)).append('\n');
	}
	
	private static int fibonacchi0(int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 0;
		}
		
		if (dp0[n] > 0) {
			return dp0[n];
		}
		
		return dp0[n] = fibonacchi0(n-1) + fibonacchi0(n-2);
	}
	
	private static int fibonacchi1(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		}
		
		if (dp1[n] > 0) {
			return dp1[n];
		}
		
		return dp1[n] = fibonacchi1(n-1) + fibonacchi1(n-2);
	}
	


	
}
