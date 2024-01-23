import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] money = new int[N+5];
		money[2] = 1;
		money[4] = 2;
		money[5] = 1;
		
		for (int i=6; i<N+1; i++) {
			if (money[i-2] != 0 && money[i-5] != 0) {
				money[i] = Math.min(money[i-2], money[i-5]) + 1;
			} else if (money[i-2] != 0) {
				money[i] = money[i-2] + 1;
			} else if (money[i-5] != 0) {
				money[i] = money[i-5] + 1;
			}
		}
		if (money[N] == 0) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(money[N]);
	}


}

