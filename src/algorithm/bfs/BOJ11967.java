package algorithm.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 *
 * 1. 아이디어
 * N * N 헛간
 * (1,1)에서 출발
 * 다른 방의 불을 켤 수 있다. 불이 켜져있는 방으로만 상하좌우 이동 가능
 * 불을 켤 수 있는 방의 최대 개수를 구하시오!!!
 * 
 * 2. 알고리즘 및 시간복잡도
 * N = 100, M = 20,000
 * (1,1)에서 불이 켜져있고, 한칸씩 불을 켜면서 돌아다니면 된다.
 * 이동은 무제한이니 아무리 돌아가도 괜찮다. 
 * 불 켤 수 있는 방의 개수는 경로와 관련없이 동일하다.
 * 한 방에 려어 스위치 있을 수 있음...
 * 맵에서 한칸에 여러 스위치를 저장할 수도 있겠네.
 * 
 * BFS로 다 돌면서 맵의 불을 밝혀가며 계속 찾아보자.
 * 시간복잡도 O(N^2) = 10,000
 * 
 * 
 * 3. 작업흐름
 * 
 * bfs에서 queue로 도는데
 * 방문했으면 continue
 * 
 * 
 * 
 */


public class BOJ11967 {

	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N, M;
	private static List<Point>[][] map;
	private static boolean[][] isLight;
	private static boolean[][] isVisit;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		setInput();
		light();
		int answer = getNumberOfLightRoom();
		System.out.println(answer);
		
	}
	
	private static void setInput() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new List[N][N];
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				map[j][i] = new ArrayList<Point>();
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[y-1][x-1].add(new Point(b-1, a-1));
	}
		
		isLight = new boolean[N][N];
		isVisit = new boolean[N][N];
	}
	
	private static void light() {
		isVisit[0][0] = true;
		isLight[0][0] = true;
		
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(0, 0));
		
		while (!queue.isEmpty()) {
			Point nowPoint = queue.poll();
			
			for (int d=0; d<4; d++) {
				int nextY = nowPoint.y + dy[d];
				int nextX = nowPoint.x + dx[d];
				
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) {
					continue;
				}
				if (isVisit[nextY][nextX] || !isLight[nextY][nextX]) {
					continue;
				}
				
				isVisit[nextY][nextX] = true;
				queue.add(new Point(nextY, nextX));
			}
			
			for (Point p : map[nowPoint.y][nowPoint.x]) {
				isLight[p.y][p.x] = true; 
				if (isVisit[p.y][p.x]) {
					continue;
				}
				
				//만약 p의 4방향에 길이 열려있다면 queue에 추가한다.
				for (int d2=0; d2<4; d2++) {	
					if (p.y + dy[d2] < 0 || p.y + dy[d2] >= N || p.x + dx[d2] < 0 || p.x + dx[d2] >= N) {
						continue;
					}
					
					if (isVisit[p.y + dy[d2]][p.x + dx[d2]]) {
						isVisit[p.y][p.x] = true;
						queue.add(new Point(p.y, p.x));
						break;
					}
				}
			}
		}
		
		
	}
	
	private static int getNumberOfLightRoom() {
		int cnt = 0;
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				if (isLight[j][i]) {
					cnt++;
				}
			}
		}
		return cnt;
	}	

}
