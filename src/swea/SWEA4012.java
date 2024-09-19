package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4012 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int[][] map;
	private static boolean[] select;
	private static int minDiff;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int T = Integer.parseInt(br.readLine());
		for (int i=1; i<T+1; i++) {
			getMinimumDiff();
			System.out.println("#" + i + " " + minDiff);
		}

	}
	
	private static int getMinimumDiff() throws NumberFormatException, IOException {
		getInput();
		
		dfs(0, 0);
		
		return 1;
	}
	
	private static void dfs(int depth, int start) {
		if (depth == N / 2) {
			minDiff = Math.min(minDiff, getDiff());
			return;
		}
		
		for (int i = start; i < N; i++) {
			select[i] = true;
			dfs(depth + 1, i + 1);
			select[i] = false;
		}
	}
	
	private static void getInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		select = new boolean[N];
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		minDiff = Integer.MAX_VALUE;
	}
	
	private static int getDiff() {

		int sumA =  0;
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				if (j != i && select[j] && select[i]) {
					sumA += map[j][i];
				}
			}
		}
		
		int sumB = 0;
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				if (j != i && !select[j] && !select[i]) {
					sumB += map[j][i];
				}
			}
		}		
		return Math.abs(sumA - sumB);
	}

}
