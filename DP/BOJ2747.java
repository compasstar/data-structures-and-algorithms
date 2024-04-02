import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int[] fibonachi;

	public static void main(String[] args) throws NumberFormatException, IOException  {
		N = Integer.parseInt(br.readLine());
		fibonachi = new int[46];
		fibonachi[0] = 0;
		fibonachi[1] = 1;
		for (int i=2; i<=N; i++) {
			fibonachi[i] = fibonachi[i-1] + fibonachi[i-2];
		}
		System.out.println(fibonachi[N]);
	}
}
