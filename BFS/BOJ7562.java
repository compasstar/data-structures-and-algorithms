import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int y;
	int x;
	
	public Point (int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {


	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static Point from;
	private static Point to;
	private static int[][] isVisit;
	
	private static final int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
	private static final int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		
		int T = Integer.parseInt(br.readLine());

		for (int t=0; t<T; t++) {
			run();
		}

	}
	
	private static void run() throws NumberFormatException, IOException {
		
		setInput();
		int move = getMinMove();
		System.out.println(move);
		
	}
	
	private static void setInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int fromY = Integer.parseInt(st.nextToken());
		int fromX = Integer.parseInt(st.nextToken());
		from = new Point(fromY, fromX);
		
		st = new StringTokenizer(br.readLine());
		int toY = Integer.parseInt(st.nextToken());
		int toX = Integer.parseInt(st.nextToken());
		to = new Point(toY, toX);
		
		isVisit = new int[N][N];
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				isVisit[j][i] = -1;
			}
		}
	}
	
	private static int getMinMove() {
		
		Queue<Point> queue = new LinkedList<>();
		queue.add(from);
		isVisit[from.y][from.x] = 0;
		
		while (!queue.isEmpty()) {
			Point nowPoint = queue.poll();
			int nowY = nowPoint.y;
			int nowX = nowPoint.x;
			if (nowY == to.y && nowX == to.x) {
				break;
			}
			
			for (int d=0; d<8; d++) {
				int nextY = nowY + dy[d];
				int nextX = nowX + dx[d];
				
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) {
					continue;
				}
				if (isVisit[nextY][nextX] > -1) {
					continue;
				}
				
				isVisit[nextY][nextX] = isVisit[nowY][nowX] + 1;
				queue.add(new Point(nextY, nextX));
			}
		}
		
		return isVisit[to.y][to.x];
	}
}


