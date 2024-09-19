package algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



class BOJ2667 {

	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	private static int totalCnt = 0;
	private static List<Integer> cnt = new ArrayList<>();

	public static void main(String args[]) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int j=0; j<N; j++) {
			String line = br.readLine();
			for (int i=0; i<N; i++) {
				map[j][i] = line.charAt(i) - '0';
			}
		}
		
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				if (map[j][i] != 0 && !visited[j][i]) {
					visited[j][i] = true;
					cnt.add(bfs(j, i));
					totalCnt++;
				}
			}
		}
		
		System.out.println(totalCnt);
		Collections.sort(cnt);
		for (Integer num : cnt) {
			System.out.println(num);
		}

	}
	
	private static int bfs(int y, int x) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x));
		
		int[] dy = {1, 0, -1, 0};
		int[] dx = {0, 1, 0, -1};
		
		int cnt = 0;
		
		while (!queue.isEmpty()) {
			Point nowPoint = queue.poll();
			int nowY = nowPoint.y;
			int nowX = nowPoint.x;
			cnt++;
			
			for (int d=0; d<4; d++) {
				int nextY = nowY + dy[d];
				int nextX = nowX + dx[d];
				
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) {
					continue;
				}
				if (visited[nextY][nextX]) {
					continue;
				}
				
				if (map[nextY][nextX] != 0) {
					queue.add(new Point(nextY, nextX));
					visited[nextY][nextX] = true;
				}
			}
			
			
		}

		return cnt;
	}

}
