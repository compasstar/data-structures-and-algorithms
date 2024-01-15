import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	private static int N;
	private static int M;
	private static int[][] miro;
	private static int[][] check;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		miro = new int[N][M];
		check = new int[N][M];
		
		for (int j=0; j<N; j++) {
			String s = br.readLine();
			for (int i=0; i<M; i++) {
				miro[j][i] = s.charAt(i) - '0';
			}
		}
		
		for (int j=0; j<N; j++) {
			for (int i=0; i<M; i++) {
				check[j][i] = 0;
			}
		}
		System.out.println(bfs(0, 0));
	}
	
	private static int bfs(int y, int x) {
		int distance;
		
		int[] dy = {1, 0, -1, 0};
		int[] dx = {0, 1, 0, -1};
		List<int[]> queue = new ArrayList<>();
		queue.add(new int[] {0, 0, 1});
		check[0][0] = 1;
		
		while (!queue.isEmpty()) {
			int ey = queue.get(0)[0];
			int ex = queue.get(0)[1];
			int preDistance = queue.get(0)[2];
			queue.remove(0);
			
			for (int d=0; d<4; d++) {
				int fy = ey + dy[d];
				int fx = ex + dx[d];
				
				if (fy >= 0 && fy < N && fx >= 0 && fx < M) {
					if (miro[fy][fx] == 1 && check[fy][fx] == 0) {
						queue.add(new int[] {fy, fx, preDistance + 1});
						check[fy][fx] = 1;
						if (fy == N-1 && fx == M-1) {
							return preDistance + 1;
						}
					}
				}
			}
		}
		return 0;
	}
}
