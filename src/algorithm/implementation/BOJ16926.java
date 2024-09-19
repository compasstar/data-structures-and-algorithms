package algorithm.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16926 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb;
	
	private static int N; 
	private static int M;
	private static int R;
	
	private static int[][] map;



	public static void main(String[] args) throws NumberFormatException, IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r=0; r<R; r++) {
			rotate();
		}
		
		for (int j=0; j<N; j++) {
			for (int i=0; i<M; i++) {
				System.out.print(map[j][i] + " ");
			}
			System.out.println();
		}	
	}
	
	private static void rotate() {
		int[][] tempMap = new int[N][M];
		
		//왼쪽으로
		for (int j=0; j<(N/2); j++) {
			for (int i=j; i<M-j-1; i++) {
				tempMap[j][i] = map[j][i+1];
			}
		}
		
		//아래로
		for (int i=0; i<(M/2); i++) {
			for (int j=i+1; j<N-i; j++) {
				tempMap[j][i] = map[j-1][i];
			}
		}
		
		//오른쪽으로
		for (int j=N-1; j>=(N/2); j--) {
			for (int i=(M-1) - (N-1-j); i>=(N-1-j) + 1; i--) {
				tempMap[j][i] = map[j][i-1];
			}
		}
		
		//위로
		for (int i=M-1; i>=(M/2); i--) {
			for (int j=N-1 - (M-1 - i) - 1; j>=(M-1-i); j--) {
				tempMap[j][i] = map[j+1][i];
			}
		}
		
		
		
		map = tempMap;
	}
	

}
