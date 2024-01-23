import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 1. 아이디어
 * 미로탈출 할 때 최소 이동횟수를 구하시오... 달이 차오른다는 뭐야 억지잖아
 * 세로 N, 가로 M
 * 열쇠와 문이 존재한다. 각 문에 대응하는 열쇠가 있으면 통과 가능 (소문자 열쇠, 대문자 문)
 * 출구가 하나가 아닐 수도 있음! 여러개일수도 있다.
 * 탈출 못하면 -1 출력
 * 
 * 우선 bfs 탐색으로 하는데 visited 를 열쇠먹은 상황에 따라 다르게 적용시킨다 -> 비트마스킹 활용
 * 
 * 
 * 2. 시간복잡도
 * 가로,세로 N, M = 50
 * bfs탐색 O(N*M)
 * 
 * 3. 작업흐름
 * char[][] map
 * int[][][64] visited 
 * 	000000 각 인덱스는 key 에 해당된다 000001 이면 a, 000011 이면 ab
 * 	visited에는 거리 값이 들어간다
 * 
 * private static bfs(y, x) 큐에 넣으며 탐색한다
 */


class Point {
	int y;
	int x;
	int key;
	int distance;
	
	public Point(int y, int x, int key, int distance) {
		this.y = y;
		this.x = x;
		this.key = key;
		this.distance = distance;
	}
}



public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	
	private static char[][] map;
	private static boolean[][][] visited;
	private static Point startPoint;
	


	
	public static void main(String[] args) throws IOException  {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][64];

		
		for (int j = 0; j < N; j++) {
			String inputLine = br.readLine();
			for (int i = 0; i < M; i++) {
				map[j][i] = inputLine.charAt(i);

				//시작 좌표는 설정에 getKeys에 넣어둔다
				if (map[j][i] == '0') {
					startPoint = new Point(j, i, 0, 0);
				}
			}
		}
		System.out.println(bfs(startPoint));
	}
	
	private static int bfs(Point startPoint) {
		
		int[] dy = {1, 0, -1, 0};
		int[] dx = {0, 1, 0, -1};
		
		visited[startPoint.y][startPoint.x][startPoint.key] = true;
		Queue<Point> queue = new LinkedList<>();
		queue.add(startPoint);
		
		
		while (!queue.isEmpty()) {
			Point nowPoint = queue.poll();
			int nowY = nowPoint.y;
			int nowX = nowPoint.x;
			int nowKey = nowPoint.key;
			int nowDistance = nowPoint.distance;
			
			for (int d=0; d<4; d++) {
				int nextY = nowY + dy[d];
				int nextX = nowX + dx[d];
				
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) {
					continue;
				}
				if (map[nextY][nextX] == '#' || visited[nextY][nextX][nowKey] == true) {
					continue;
				}
				
				//key 를 먹는다면, 문을 만난다면
				if (map[nextY][nextX] - 'a' >= 0 && map[nextY][nextX] - 'a' < 6) {
					int nextKey = nowKey | 1 << (map[nextY][nextX] - 'a'); 
					visited[nextY][nextX][nextKey] = true;
					queue.add(new Point(nextY, nextX, nextKey, nowDistance + 1));
				} else if (map[nextY][nextX] - 'A' >= 0 && map[nextY][nextX] - 'A' < 6) {
					if ((nowKey & (1 << (map[nextY][nextX] - 'A'))) == (1 << (map[nextY][nextX] - 'A'))) {
						visited[nextY][nextX][nowKey] = true;
						queue.add(new Point(nextY, nextX, nowKey, nowDistance + 1));
					}
				} else if (map[nextY][nextX] == '1') {
					return nowDistance + 1;
				} else {
					visited[nextY][nextX][nowKey] = true;
					queue.add(new Point(nextY, nextX, nowKey, nowDistance + 1));
				}
			}	
		}
		return -1;
	}
}

