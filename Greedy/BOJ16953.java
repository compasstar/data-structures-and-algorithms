import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static long A, B;
	private static int minCount;


	public static void main(String[] args) throws Exception {
		setInput();
		getMinNumberOfChange(1, A);
		if (minCount == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(minCount);
	}
	
	private static void setInput() throws Exception {
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		minCount = Integer.MAX_VALUE;
	}
	
	private static void getMinNumberOfChange(int cnt, long number) {
		if (number == B) {
			minCount = Math.min(minCount, cnt);
			return;
		} else if (number > B) {
			return;
		}
		
		getMinNumberOfChange(cnt + 1, number * 2);
		
		long nextNumber = Long.parseLong(Long.toString(number) + "1");
		getMinNumberOfChange(cnt + 1, nextNumber);
	}
}
