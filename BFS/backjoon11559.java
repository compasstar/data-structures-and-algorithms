import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * bfs 로 주위에 4개 이상 뭉쳐있는지 확인
 * 뭉쳐있다면 그것들을 없애고 위에 있는 것들이 내려온다
 * 연쇄가 일어나지 않을때까지 반복
 * 
 * 2. 시간복잡도
 * bfs 를 연쇄가 끝날때까지 반복한다
 * O(72 * 연쇄) 작다
 * 
 * 3. 작업흐름
 * 맵을 돌면서 색깔이라면 bfs 실행
 * 연쇄가 일어나면 반영
 * 연쇄가 일어나지 않으면 return 연쇄
 */

class Point {
	int y;
	int x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	
	private static final int MAX_ROW = 12;
	private static final int MAX_COLUMN = 6;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static char[][] map = new char[MAX_ROW][MAX_COLUMN];
	private static boolean[][] visited;
	private static int chain = 0;
	
	public static void main(String[] args) throws IOException {
		
		for (int j=0; j<MAX_ROW; j++) {
			String line = br.readLine();
			for (int i=0; i<MAX_COLUMN; i++) {
				map[j][i] = line.charAt(i);
			}

		}
		
		while (true) {
			visited = new boolean[MAX_ROW][MAX_COLUMN];
			
			for (int j=0; j<MAX_ROW; j++) {
				for (int i=0; i<MAX_COLUMN; i++) {
					if (map[j][i] != '.' && visited[j][i] == false) {
						boom(j, i);
					}
				}
			}
			
			if (!down()) {
				break;
			}
			chain++;
			
//			for (int j=0; j<MAX_ROW; j++) {
//				for (int i=0; i<MAX_COLUMN; i++) {
//					System.out.print(map[j][i]);
//				}
//				System.out.println();
//			}
//			System.out.println("\n");
		}
		
		System.out.println(chain);
	}
	
	private static void boom(int y, int x) {
		
		
		int[] dy = {1, 0, -1, 0};
		int[] dx = {0, 1, 0, -1};
		
		int cnt = 0;
		
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(y, x));
		visited[y][x] = true;
		
		List<Point> visitedList = new ArrayList<>();
		visitedList.add(new Point(y, x));
		
		
		while (!queue.isEmpty()) {
			Point nowPoint = queue.poll();
			int nowY = nowPoint.y;
			int nowX = nowPoint.x;
			cnt++;
			
			for (int d=0; d<4; d++) {
				int nextY = nowY + dy[d];
				int nextX = nowX + dx[d];
				
				if (nextY < 0 || nextY >= MAX_ROW || nextX < 0 || nextX >= MAX_COLUMN) {
					continue;
				}
				if (visited[nextY][nextX]) {
					continue;
				}
				if (map[y][x] == map[nextY][nextX]) {
					visited[nextY][nextX] = true;
					queue.add(new Point(nextY, nextX));
					visitedList.add(new Point(nextY, nextX));
				}
			}
		}
		
		if (cnt >= 4) {
			for (Point p : visitedList) {
				map[p.y][p.x] = 'Z'; 
			}
		}
	}
	
	
	private static boolean down() {
		boolean sw = false;
		for (int i=0; i<MAX_COLUMN; i++) {
			for (int j=MAX_ROW - 1; j>=0; j--) {
				while (map[j][i] == 'Z') {
					sw = true;
					for (int k=j; k>0; k--) {
						map[k][i] = map[k-1][i];
					}
					map[0][i] = '.';
				}
			}
		}
		return sw;
	}

}
