package swea; /**
 * 과자를 두 봉지 사는데, M보다는 작은 최댓값을 구하라.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA9229 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N; // 과자 개수
	private static int M; // 무게 합 제한
	private static int[] snacks;
	private static int maxWeight;

	

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int T = Integer.parseInt(br.readLine());
		for (int i=1; i<T+1; i++) {
			run();
			System.out.println("#" + i + " " + maxWeight);
		}


	}
	
	private static void run() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		snacks = new int[N];
		maxWeight = -1;
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			snacks[i] = Integer.parseInt(st.nextToken());
		}
		
		
		for (int j=0; j<N; j++) {
			for (int i=j+1; i<N; i++) {
				if (snacks[j] + snacks[i] <= M) {
					maxWeight = Math.max(maxWeight, snacks[j] + snacks[i]);
				}
			}
		}
	}
}
