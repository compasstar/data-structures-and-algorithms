package swea; /**
 * 1. 아이디어
 * S: 출발지, G: 도착지
 * 깊이 1 = 복구 1
 * 도착지까지 복구 시간을 가장 짧게 하여 도착하여라!
 * 
 * 2. 알고리즘 및 시간복잡도
 * 그래프에서 한 정점에서 다른 정점까지 최단거리 --> 다익스트라
 * 시간복잡도 = 맵의 크기 = N * N = 100 * 100 = 10^4
 * 
 * 3. 작업흐름
 * 
 * int getMinDistance(출발지, 도착지) : 다익스트라
 * 
 */

import java.util.*;
import java.io.*;


public class SWEA1249 {

	static class Point implements Comparable<Point>{
		int y;
		int x;
		int d;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		public Point(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}

		@Override
		public int compareTo(Point o) {
			return this.d - o.d;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int N;
	private static int[][] map;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};
	private static final Integer INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<T+1; t++) {
			run(t);
		}
	}
	
	private static void run(int t) throws Exception {
		setInput();
		int distance = getMinDistance();
		
		System.out.println("#" + t + " " + distance);
	}
	
	private static void setInput() throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int j=0; j<N; j++) {
			char[] row = br.readLine().toCharArray();
			for (int i=0; i<N; i++) {
				map[j][i] = row[i] - '0';
			}
		}
	}
	
	private static int getMinDistance() {
		
		int[][] distance = new int[N][N];
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				distance[j][i] = INF;
			}
		}
		distance[0][0] = 0;
		boolean[][] isVisit = new boolean[N][N];
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 0));
		
		while (!pq.isEmpty()) {
			Point nowPoint = pq.poll();
			int nowY = nowPoint.y;
			int nowX = nowPoint.x;
			if (isVisit[nowY][nowX]) {
				continue;
			}
			isVisit[nowY][nowX] = true;
			
			for (int d=0; d<4; d++) {
				int nextY = nowY + dy[d];
				int nextX = nowX + dx[d];
				
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >=N) {
					continue;
				}
				
				if (distance[nextY][nextX] > distance[nowY][nowX] + map[nextY][nextX]) {
					distance[nextY][nextX] = distance[nowY][nowX] + map[nextY][nextX];
					pq.add(new Point(nextY, nextX, distance[nextY][nextX]));
				}
			}
		}
		
		return distance[N-1][N-1];
	}

}
