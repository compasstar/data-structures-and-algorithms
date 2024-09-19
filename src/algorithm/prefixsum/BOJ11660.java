package algorithm.prefixsum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ11660 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N, M;
	private static int[][] map;
	private static int[][] prefixMap;
	

	public static void main(String[] args) throws Exception {
		setInput();
	}
	
	private static void setInput() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		prefixMap = new int[N][N];
		setPrefixMap();
		 
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int answer = getPrefixSum(y1, x1, y2, x2);
			System.out.println(answer);
		}
	}
	
	private static void setPrefixMap() {
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				if (j == 0 && i == 0) {
					prefixMap[j][i] = map[j][i];
				} else if (j == 0) {
					prefixMap[j][i] = map[j][i] + prefixMap[j][i-1];
				} else if (i == 0) {
					prefixMap[j][i] = map[j][i] + prefixMap[j-1][i];
				} else {
					prefixMap[j][i] = map[j][i] + prefixMap[j-1][i] + prefixMap[j][i-1] - prefixMap[j-1][i-1];
				}
			}
		}
	}
	
	private static int getPrefixSum(int y1, int x1, int y2, int x2) {
		y1--;
		x1--;
		y2--;
		x2--;
		
		if (y1 == 0 && x1 == 0) {
			return prefixMap[y2][x2];
		} else if (y1 == 0) {
			return prefixMap[y2][x2] - prefixMap[y2][x1 - 1];
		} else if (x1 == 0) {
			return prefixMap[y2][x2] - prefixMap[y1 - 1][x2];
		}
		
		return prefixMap[y2][x2] - prefixMap[y2][x1 - 1] - prefixMap[y1 - 1][x2] + prefixMap[y1 - 1][x1 - 1];
	}
}
