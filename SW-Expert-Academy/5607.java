import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N, R;
	private static long[] factorial;
	private static final long MOD = 1234567891;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<T+1; t++) {
			long answer = run();
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	private static long run() throws NumberFormatException, IOException{
		setInput();
		return combination(N, R);
	} 
	
	private static void setInput() throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		factorial = new long[1000001];
		factorial[0] = 1;
		for (int i=1; i<1000001; i++) {
			factorial[i] = (factorial[i - 1] * i) % MOD;
		}
	}
	
	/**
	 * n!  *  r!(n-r)!^(p-2)
	 */
	private static long combination(int n, int r) {	
		long left = factorial[n];
		long right = ((factorial[r] % MOD) * (factorial[n-r] % MOD)) % MOD;
		right = power(right, MOD - 2) % MOD;
		return (left * right) % MOD;
	}
	
	private static long power(long bottom, long pow) {
		if (pow == 0) {
			return 1;
		}
		
		long value = 0;
		long half = power(bottom, pow / 2);
		
		if (pow % 2 == 0) {
			value = ((half % MOD) * (half % MOD)) % MOD;
		} else if (pow % 2 == 1) {
			value = ((half % MOD) * (half % MOD)) % MOD;
			value *= bottom;
			value %= MOD;
		}
		
		return value;
	}

}
