package algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ1600 {

	static class Point {
		int y;
		int x;
		int k;
		int move;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public Point(int y, int x, int k, int move) {
			this.y = y;
			this.x = x;
			this.k = k;
			this.move = move;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int K; //남은 움직임
	private static int W; //가로
	private static int H; //세로

	private static int[][] map;
	private static boolean[][][] visited;
	private static int minMove;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};
	private static final int[] Kdy = {2, 1, 2, 1, -2, -1, -2, -1};
	private static final int[] Kdx = {1, 2, -1, -2, 1, 2, -1, -2};

	public static void main(String[] args) throws IOException {
		getInput();	
		move();
		
		if (minMove == Integer.MAX_VALUE) {
			minMove = -1;
		}
		
		System.out.println(minMove);
	}
	
	private static void getInput() throws NumberFormatException, IOException {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		for (int j=0; j<H; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<W; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[H][W][K+1];
		minMove = Integer.MAX_VALUE;
	}
	
	
	private static void move() {
		
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, K, 0));
		visited[0][0][K] = true;
		
		while (!queue.isEmpty()) {
			
			Point nowPoint = queue.poll();
			int nowY = nowPoint.y;
			int nowX = nowPoint.x;
			int nowK = nowPoint.k;
			int nowMove = nowPoint.move;
			
			if (nowY == H - 1 && nowX == W - 1) {
				minMove = Math.min(minMove, nowMove);
			}
			
			for (int d=0; d<4; d++) {
				int nextY = nowY + dy[d];
				int nextX = nowX + dx[d];
				
				if (nextY < 0 || nextY >= H || nextX < 0 || nextX >= W) {
					continue;
				}
				if (visited[nextY][nextX][nowK] || map[nextY][nextX] == 1) {
					continue;
				}
				
				visited[nextY][nextX][nowK] = true;
				queue.add(new Point(nextY, nextX, nowK, nowMove + 1));	
			}
			
			if (nowK == 0) {
				continue;
			}
			
			for (int d=0; d<8; d++) {
				int nextY = nowY + Kdy[d];
				int nextX = nowX + Kdx[d];
				int nextK = nowK - 1;
				
				if (nextY < 0 || nextY >= H || nextX < 0 || nextX >= W) {
					continue;
				}
				if (visited[nextY][nextX][nextK] || map[nextY][nextX] == 1) {
					continue;
				}
				
				visited[nextY][nextX][nextK] = true;
				queue.add(new Point(nextY, nextX, nextK, nowMove + 1));	
			}
			
			
		}
		
	}
	
}
