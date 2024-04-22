import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 정사각형 지도
 * 1 -> 나무, 0 -> 빈 공간 , B -> 기차, E -> 목표점
 * 기차의 길이 = B의 개수 = E의 개수 = 3
 * 움직이는 방식: U, D, L, R, T 
 * 기차는 열이든 행이든 무조건 일렬로만 놓여있다.
 * 
 * 회전규칙
 * 가운데 중심점을 기준으로 회전한다
 * 3*3 구역에 하나의 나무도 있어서는 안 된다.
 * 
 * 기차를 움직이며 최소 동작 횟수를 구하시오!!
 * 만약 도착하지 못한다면 0을 출력한다.
 * 
 * 2. 알고리즘 및 시간복잡도
 * N = 한 변의 길이
 * int[N][N] map;
 * 
 * 음... 기차를 3칸 전부 움직이는게 아니라, 가운데 점만 기준으로 움직이면 될 것 같은데?
 * 그리고 현재 가로모드인지 세로모드인지만 체크하면 되잖아.
 * 중심점을 E의 중심점으로 옮긴다음에 방향에 맞추어 Turn 하면 끝!
 * 
 * 이제 빠른 방식을 찾아야 하는데 1이 가로막고 있어서 직선으로 가면 안된단 말이지
 * 그렇다면 BFS를 통해 접근하면 될 것 같다.
 * 시간복잡도 = N*N = 50*50 = 2500
 * 
 * 
 * 3. 작업흐름
 * 시작 중심점 좌표 찾기
 * 현재 열차 방향 (가로세로)
 * 
 * 도착 중심점 좌표 찾기
 * 도착 열차 방향(가로세로)
 * 
 * BFS
 * int getMinMove()
 * 
 * 
 */

class Point {
	int y;
	int x;
	int direction; // 0이면 세로, 1이면 가로

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	public Point(int y, int x, int direction) {
		this.y = y;
		this.x = x;
		this.direction = direction;
	}
}

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	private static int N;
	private static int[][] map;
	private static Point startPoint;
	private static Point endPoint;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};
	private static final Integer DIRECTION_COLUMN = 0;
	private static final Integer DIRECTION_ROW = 1;
	
	
	public static void main(String[] args) throws Exception {
		setInput();
		int answer = getMinMove();
		System.out.println(answer);
	}
	
	private static void setInput() throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		Point[] bPoints = new Point[3];
		Point[] ePoints = new Point[3];
		int bIndex = 0;
		int eIndex = 0;
		
		for (int j=0; j<N; j++) { 
			char[] line = br.readLine().toCharArray();
			for (int i=0; i<N; i++) {
				if (line[i] == '0') {
					map[j][i] = 0;
				} else if (line[i] == '1') {
					map[j][i] = 1;
				} else if (line[i] == 'B') {
					bPoints[bIndex++] = new Point(j, i);
					map[j][i] = 0;
				} else if (line[i] == 'E') {
					ePoints[eIndex++] = new Point(j, i);
					map[j][i] = 0;
				}
			}
		}
		
		if (bPoints[0].y == bPoints[1].y) {
			startPoint = new Point(bPoints[1].y, bPoints[1].x, DIRECTION_ROW);
		} else {
			startPoint = new Point(bPoints[1].y, bPoints[1].x, DIRECTION_COLUMN);
		}
		
		if (ePoints[0].y == ePoints[1].y) {
			endPoint = new Point(ePoints[1].y, ePoints[1].x, DIRECTION_ROW);
		} else {
			endPoint = new Point(ePoints[1].y, ePoints[1].x, DIRECTION_COLUMN);
		}
		
		
	}
	
	
	// BFS
	private static int getMinMove() {
		//지도, 방향
		boolean[][][] isVisit = new boolean[N][N][2];
		int[][][] move = new int[N][N][2];
		isVisit[startPoint.y][startPoint.x][startPoint.direction] = true;
		
		Queue<Point> queue = new LinkedList<>();
		queue.add(startPoint);
		
		while (!queue.isEmpty()) {
			Point nowPoint = queue.poll();
			
			//상하좌우
			for (int d=0; d<4; d++) {
				int nextY = nowPoint.y + dy[d];
				int nextX = nowPoint.x + dx[d];
				
				if (nowPoint.direction == DIRECTION_COLUMN) {
					if (nextY - 1 < 0 || nextY + 1 >= N || nextX < 0 || nextX >= N) {
						continue;
					}
					if (map[nextY - 1][nextX] == 1 || map[nextY][nextX] == 1 || map[nextY + 1][nextX] == 1) {
						continue;
					}
				} else if (nowPoint.direction == DIRECTION_ROW) {
					if (nextY < 0 || nextY >= N || nextX - 1 < 0 || nextX + 1 >= N) {
						continue;
					}
					if (map[nextY][nextX - 1] == 1 || map[nextY][nextX] == 1 || map[nextY][nextX + 1] == 1) {
						continue;
					}
				}
				
				if (isVisit[nextY][nextX][nowPoint.direction]) {
					continue;
				}
				
				if (nextY == endPoint.y && nextX == endPoint.x && nowPoint.direction == endPoint.direction) {		
					return move[nowPoint.y][nowPoint.x][nowPoint.direction] + 1;
				}
				
				isVisit[nextY][nextX][nowPoint.direction] = true;
				move[nextY][nextX][nowPoint.direction] = move[nowPoint.y][nowPoint.x][nowPoint.direction] + 1;
				queue.add(new Point(nextY, nextX, nowPoint.direction));
			}
			
			//방향 전환
			int nextDirection;
			if (nowPoint.direction == DIRECTION_ROW) {
				nextDirection = DIRECTION_COLUMN;
			} else {
				nextDirection = DIRECTION_ROW;
			}
			
			if (isVisit[nowPoint.y][nowPoint.x][nextDirection]) {
				continue;
			}
			
			boolean sw = true;
			for (int j=-1; j<=1; j++) {
				for (int i=-1; i<=1; i++) {
					int nextY = nowPoint.y + j;
					int nextX = nowPoint.x + i;
					
					if (nextY - 1 < 0 || nextY + 1 >= N || nextX < 0 || nextX >= N) {
						sw = false;
						continue;
					}
					
					if (map[nextY][nextX] == 1) {
						sw = false;
					}
				}
			}
			
			if (sw) {
				if (nowPoint.y == endPoint.y && nowPoint.x == endPoint.x && nextDirection == endPoint.direction) {
					return move[nowPoint.y][nowPoint.x][nowPoint.direction] + 1;
				}
				
				isVisit[nowPoint.y][nowPoint.x][nextDirection] = true;
				move[nowPoint.y][nowPoint.x][nextDirection] = move[nowPoint.y][nowPoint.x][nowPoint.direction] + 1;
				queue.add(new Point(nowPoint.y, nowPoint.x, nextDirection));
			}
		}
		
		
		return 0;
	}

}
