import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * - 정사각형 블록, 삼각형 블록, 웜홀, 블랙홀
 * - 웜홀에 빠지면 동일한 숫자를 가진 다른 웜홀로 빠져나온다
 * - 블랙홀을 만나면 게임 종료, 출발 위치로 돌아와도 게임 종료
 * - 벽이나 블록에 부딪힐 때마다 점수 + 1
 * - 시작위치, 진행 방향 임의로 선정 가능
 * - 게임에서 얻을 수 있는 점수의 최댓값을 구하시오!!
 * 
 * 
 * 2. 시간복잡도
 * - 구현 문제. 맵을 그냥 규칙에 따라 돌면 된다
 * - 한 게임당 N*N, 모든 위치에서 4방향으로 게임을 진행해야 함으로 --> (N*N) * (N*N*4) = 4 * 10^8
 * - Worst Case만 아니라면 시간은 괜찮을 것이다... 아마? 그러니 최적화를 하자. 3초니까 괜찮을 것 같다.
 * 
 * 3. 작업흐름
 * 
 * class WarmHall() {
 * int id, int y1, int x1, int y2, int x2
 * }
 * 
 * 
 * int N
 * int[][] map
 * int[] dy = {1, 0, -1, 0}
 * int[] dx = {0, 1, 0, -1}
 * int startY startX
 * int maxPoint
 * HashMap<id, WarmHall> warmHalls: 웜홀 번호로 y1 x1 y2 x2 를 저장하고 있다
 * 
 * 
 * 맵 돌면서 WarmHall 채워놓기
 * 
 * map의 모든 좌표에서 4방향으로 game 실행
 * 실행할 때 startY, startX 를 -1(블랙홀)로 변경한다 (끝나고 원상복귀)
 * 
 * 
 * int game(int y, int x, int dir)
 * point = 0
 * 
 * while 문
 * 
 * nextY = nowY + dy[dir]
 * nextX = nowX + dx[dir]
 * 
 * 블랙홀 또는 시작 위치를 만나는 경우
 * return point
 *
 * 웜홀을 만나는 경우
 * 웜홀 해시맵에서 웜홀의 두 좌표를 구하고, 현재 좌표랑 동일하지 않은 곳으로 이동시킨다
 * 
 * 벽을 만나거나 정사각형 블록을 만나는 경우
 * dir = (dir + 2) % 4
 * point++
 * 
 * 
 * 삼각형 블록을 만나는 경우
 * - 방향은 위, 삼각형 1, 4 -->반대방향
 * - 방향은 위, 삼각형 2 --> dir = 1
 * - 방향은 위, 삼각형 3 --> dir = 3
 * 
 * - 방향은 오른쪽, 삼각형 1, 2 --> 반대방향
 * - 방향은 오른쪽, 삼각형 4 --> dir = 0
 * - 방향은 오른쪽, 삼각형 3 --> dir = 2
 * 
 * - 방향은 아래, 삼각형 2, 3 --> 반대방향
 * - 방향은 아래, 삼각형 1 -> dir = 1
 * - 방향은 아래, 삼각형 4 --> dir = 3
 * 
 * - 방향은 왼쪽, 삼각형 3, 4, --> 반대방향
 * - 방향은 왼쪽, 삼각형 1 --> dir = 0
 * - 방향은 왼쪽, 삼각형 2 --> dir = 2
 * 
 * point++
 * 
 * 
 */

class Point {
	int y;
	int x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class WarmHall {
	Point p1;
	Point p2;
	
	public WarmHall() {

	}
	
	public void add(Point p) {
		if (p1 == null) {
			this.p1 = p;
		} else if (p2 == null) {
			this.p2 = p;
		}
	}
	
	public Point move(Point p) {
		
		if (this.p1.y == p.y && this.p1.x == p.x) {
			return p2;
		} else if (this.p2.y == p.y && this.p2.x == p.x) {
			return p1;
		}
		
		//에러 발생 (현재 위치가 웜홀이 아님)
		return null;
	}
}

class Solution {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int[][] map;
	private static int maxPoint;
	private static Map<Integer, WarmHall> warmHalls;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};
	



	public static void main(String args[]) throws NumberFormatException, IOException {
		
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t < T+1; t++) {
			sb.append('#').append(t).append(' ').append(run()).append('\n');
		}
		System.out.println(sb);
	
	}
	
	private static int run() throws NumberFormatException, IOException {
		
		getInput();
		
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				
				if (map[j][i] == 0) {
					map[j][i] = -1;
					for (int d=0; d<4; d++) {
						int result = game(j, i, d);
						maxPoint = Math.max(maxPoint, result);					
					}
					map[j][i] = 0;
				}
			}
		}
		
		
		return maxPoint;
	}
	
	private static void getInput() throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		warmHalls = new HashMap<>();
		for (int i=6; i<=10; i++) {
			warmHalls.put(i, new WarmHall());
		}
		
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int i=0; i<N; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
				
				if (map[j][i] >= 6 && map[j][i] <= 10) {
					warmHalls.get(map[j][i]).add(new Point(j, i));
				}
			}
		}
		
		maxPoint = 0;
	}
	
	private static int game(int y, int x, int dir) {
		int point = 0;
		
		int nowY = y;
		int nowX = x;
		while (true) {
			
			int nextY = nowY + dy[dir];
			int nextX = nowX + dx[dir];
			
			//벽 또는 정사각형을 만나는 경우: 방향만 바뀌고 현재 위치에 그대로 있는다. 포인트 + 1
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N || map[nextY][nextX] == 5) {
				dir = (dir + 2) % 4;
				point++;
			}
			
			//블랙홀 또는 시작 위치를 만나는 경우
			else if (map[nextY][nextX] == -1) {
				break;
			}
			
			//웜홀을 만나는 경우
			else if (map[nextY][nextX] >= 6 && map[nextY][nextX] <= 10) {
				Point p = warmHalls.get(map[nextY][nextX]).move(new Point(nextY, nextX));
				nextY = p.y;
				nextX = p.x;
			}
			
			//삼각형을 만나는 경우: 삼각형 위치에 핀볼이 위치하게 된다. 포인트 + 1
			else if (map[nextY][nextX] >= 1 && map[nextY][nextX] <= 4) {
				
				//방향 아래
				if (dir == 0) {
					if (map[nextY][nextX] == 2 || map[nextY][nextX] == 3) {
						dir = (dir + 2) % 4;
					} else if (map[nextY][nextX] == 1) {
						dir = 1;
					} else if (map[nextY][nextX] == 4) {	
						dir = 3;
					}
				}
				
				
				//방향 오른쪽
				else if (dir == 1) {
					if (map[nextY][nextX] == 1 || map[nextY][nextX] == 2) {
						dir = (dir + 2) % 4;
					} else if (map[nextY][nextX] == 4) {
						dir = 2;
					} else if (map[nextY][nextX] == 3) {
						dir = 0;
					}
				}
				
				//방향 위
				else if (dir == 2) {
					if (map[nextY][nextX] == 1 || map[nextY][nextX] == 4) {
						dir = (dir + 2) % 4;
					} else if (map[nextY][nextX] == 2) {
						dir = 1;
					} else if (map[nextY][nextX] == 3) {
						dir = 3;
					}
				}
				
				//방향 왼쪽
				else if (dir == 3) {
					if (map[nextY][nextX] == 3 || map[nextY][nextX] == 4) {
						dir = (dir + 2) % 4;
					} else if (map[nextY][nextX] == 1) {
						dir = 2;
					} else if (map[nextY][nextX] == 2) {
						dir = 0;
					}
				}
				point++;
			}
			
			nowY = nextY;
			nowX = nextX;
		}
		
		
		
		
		return point;
	}
	


}
