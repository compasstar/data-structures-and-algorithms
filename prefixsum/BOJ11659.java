import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int[] numbers;
	
	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] prefixNumbers = new int[N];
		for (int i=0; i<N; i++) {
			if (i == 0) {
				prefixNumbers[i] = numbers[i];
				continue;
			}
			
			prefixNumbers[i] = prefixNumbers[i-1] + numbers[i];
		}
		
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 2;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			if (from == -1) {
				System.out.println(prefixNumbers[to]);
			} else {
				System.out.println(prefixNumbers[to] - prefixNumbers[from]);
			}
		}
	}


}


