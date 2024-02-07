import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int y;
	int x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	private static int time = 0;
	private static int[][] cheeze;
	private static boolean[][] visited;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException  {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheeze = new int[N][M];
		
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				cheeze[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Integer> meltList = new ArrayList<>();
		
		while(true) {
			bfs(0, 0);
	
			int cnt = melt();
			meltList.add(cnt);
			if (cnt == 0) {
				System.out.println(time);
				System.out.println(meltList.get(meltList.size() - 2));
				break;
			}
			time++;
		}

	}
	
	private static void bfs(int y, int x) {
		visited = new boolean[N][M];
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x));
		visited[y][x] = true;
		
		while (!queue.isEmpty()) {
			Point nowPoint = queue.poll();
			int nowY = nowPoint.y;
			int nowX = nowPoint.x;
			
			for (int d=0; d<4; d++) {
				int nextY = nowY + dy[d];
				int nextX = nowX + dx[d];
				
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || visited[nextY][nextX]) {
					continue;
				}
				if (cheeze[nextY][nextX] == 0) {
					visited[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX));
				} else if (cheeze[nextY][nextX] == 1) {
					cheeze[nextY][nextX] = 2;
				}
			}
			
		}
	}
	
	private static int melt() {
		int cnt = 0;
		for (int j=0; j<N; j++) {
			for (int i=0; i<M; i++) {
				if (cheeze[j][i] == 2) {
					cheeze[j][i] = 0;
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	
	private static void printCheeze() {
		
		for (int j=0; j<N; j++) {
			for (int i=0; i<M; i++) {
				System.out.print(cheeze[j][i] +  " ");
			}
			System.out.println();
		}
		
	}
	


	

}
