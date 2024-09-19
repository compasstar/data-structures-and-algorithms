package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ17069 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int[][] map;
	private static long dp[][][];


	public static void main(String[] args) throws NumberFormatException, IOException {
		
		getInput();

		long answer = move(N-1, N-1, 0) + move(N-1, N-1, 1) + move(N-1, N-1, 2);
		System.out.println(answer);
	}
	
	private static void getInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new long[N][N][3];

		for (int k=0; k<3; k++) {
			for (int j=0; j<N; j++) {
				for (int i=0; i<N; i++) {
					dp[j][i][k] = -1;
				}
			}
		}
		
		dp[0][1][0] = 1L;
	}
	
	private static long move(int y, int x, int type) {
		
		if (y == 0 && x == 1 && type == 0) {
			return 1L;
		}
		
		
		if (y < 0 || x < 0 || map[y][x] == 1) {
			return 0;
		}
		if (type == 0 && (x-1 < 0 || map[y][x-1] == 1)) {
			return 0;
		} 
		if (type == 1 && (y-1 < 0 || map[y-1][x] == 1)) {
			return 0;
		}
		if (type == 2 && (y-1 < 0 || x-1 < 0 || map[y-1][x] == 1 || map[y][x-1] == 1)) {
			return 0;
		}
		
		if (dp[y][x][type] != -1) {
			return dp[y][x][type];
		}
		
		
		if (type == 0) {
			return dp[y][x][type] = move(y, x-1, 0) + move(y, x-1, 2);
		} else if (type == 1) {
			return dp[y][x][type] = move(y-1, x, 1) + move(y-1, x, 2);
		} else {
			return dp[y][x][type] = move(y-1, x-1, 0) + move(y-1, x-1, 1) + move(y-1, x-1, 2);
		}

	}
}
