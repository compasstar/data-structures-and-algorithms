
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb;
	
	private static int N; 
	private static int M;
	private static int R;
	
	private static int[][] map;
	private static int[][] tempMap;



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

		rotate2();
		
		StringBuilder sb = new StringBuilder();
		for (int j=0; j<N; j++) {
			for (int i=0; i<M; i++) {
				sb.append(map[j][i]).append(' ');
			}
			sb.append('\n');
		}	
		System.out.println(sb);
	}
	
	
	private static void rotate2() {
		int cnt = 0;
		int minLength = Math.min(N/2, M/2);
		

		while (cnt < minLength) {
			int rotateNumber = R % (2*(M+N-2) - cnt*8);
			
			for (int r=0; r<rotateNumber; r++) {
				rotateLine(cnt);
			}
			cnt++;
		}

	}
	

	private static void rotateLine(int cnt) {
		
		int[] dy = {0, 1, 0, -1};
		int[] dx = {1, 0, -1, 0};

		int start = map[cnt][cnt];
		
		int y = cnt;
		int x = cnt;
		int d = 0;
		while (d<4) {

	
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if (ny >= cnt && ny < N-cnt && nx >= cnt && nx < M-cnt) {
				map[y][x] = map[ny][nx];
				y = ny;
				x = nx;
			} else {
				d++;
			}
		}
		map[cnt+1][cnt] = start;
	}

}
