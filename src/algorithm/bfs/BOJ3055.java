package algorithm.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



public class BOJ3055 {

	static class Point {
		int y;
		int x;
		int time;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
			this.time = 0;
		}

		public Point(int y, int x, int time) {
			this.y = y;
			this.x = x;
			this.time = time;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int R, C;
	private static char[][] map;
	private static boolean[][] visited;
	private static Point D;
	private static Point S;
	
	private static Queue<Point> queueWater;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};
	

	public static void main(String[] args) throws Exception  {
		setInput();
		
		int answer = move();
		if (answer == -1) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(answer);
		}

	}
	
	private static void setInput() throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		visited = new boolean[R][C];
		
		queueWater = new LinkedList<>();
		
		for (int j=0; j<R; j++) {
			char[] line = br.readLine().toCharArray();
			for (int i=0; i<C; i++) {
				map[j][i] = line[i];
				if (map[j][i] == 'D') {
					D = new Point(j, i);
				} else if (map[j][i] == 'S') {
					S = new Point(j, i);
				} else if (map[j][i] == '*') {
					queueWater.add(new Point(j, i));
					visited[j][i] = true;
				}
			}
		}
	}
	
	private static int move() {
		
		Queue<Point> queueDochi = new LinkedList<>();
		queueDochi.add(S);
		visited[S.y][S.x] = true;
		
		int nowTime = 0;
		
		while (!queueWater.isEmpty() || !queueDochi.isEmpty()) {
			while (!queueWater.isEmpty() && queueWater.peek().time == nowTime) {
				Point nowWater = queueWater.poll();
				for (int d=0; d<4; d++) {
					if (nowWater != null) {
						int nextY = nowWater.y + dy[d];
						int nextX = nowWater.x + dx[d];
						
						if (!checkWater(nextY, nextX)) {
							continue;
						}
						visited[nextY][nextX] = true;
						queueWater.add(new Point(nextY, nextX, nowTime + 1));
					}
				}
			}
			
			while (!queueDochi.isEmpty() && queueDochi.peek().time == nowTime) {
				Point nowDochi = queueDochi.poll();
				for (int d=0; d<4; d++) {
					if (nowDochi != null) {
						int nextY = nowDochi.y + dy[d];
						int nextX = nowDochi.x + dx[d];
				
						if (!checkDochi(nextY, nextX)) {
							continue;
						}
						visited[nextY][nextX] = true;
						if (map[nextY][nextX] == 'D') {
							return nowTime + 1;
						}
						queueDochi.add(new Point(nextY, nextX, nowTime + 1));
					}
				}
			}
			
			nowTime++;
		}
		
		return -1;
	}
	
	private static boolean checkDochi(int y, int x) {
		if (y < 0 || y >= R || x < 0 || x >= C) {
			return false;
		}
		if (visited[y][x]) {
			return false;
		}
		if (map[y][x] == 'X' || map[y][x] == '*') {
			return false;
		}
		
		return true;
	}
	
	private static boolean checkWater(int y, int x) {
		if (y < 0 || y >= R || x < 0 || x >= C) {
			return false;
		}
		if (visited[y][x]) {
			return false;
		}
		if (map[y][x] == 'X' || map[y][x] == 'D') {
			return false;
		}
		
		return true;
	}

}
