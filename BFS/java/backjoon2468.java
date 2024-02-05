import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
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
	private static int[][] map;
	private static boolean[][] visited;
	private static int minHeight = Integer.MAX_VALUE;
	private static int maxHeight = 0;
	private static int safeRegion;
	private static int maxSafeRegion = 0;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};


	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
				minHeight = Math.min(minHeight, map[j][i]);
				maxHeight = Math.max(maxHeight, map[j][i]);
			}
		}
		
		
		for (int h=minHeight - 1; h<=maxHeight; h++) {
			visited = new boolean[N][N];
			safeRegion = 0;
			
			for (int j=0; j<N; j++) {
				for (int i=0; i<N; i++) {
					if (map[j][i] > h && !visited[j][i]) {
						getRegion(j, i, h);
						safeRegion++;
					}
				}
			}
			
			maxSafeRegion = Math.max(maxSafeRegion, safeRegion);
		}
		
		System.out.println(maxSafeRegion);
	}
	
	private static void getRegion(int y, int x, int h) {
		
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
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N || visited[nextY][nextX]) {
					continue;
				}
				if (map[nextY][nextX] > h) {
					visited[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX));
				}
			}
		}


	}

}
