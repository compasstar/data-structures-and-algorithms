package algorithm.dp; /**
 * 1. 아이디어
 * 높이가 더 낮은 지점으로만 이동한다
 * 목표지점까지 이동하는 경로의 개수를 구하시오!
 * 
 * 2. 알고리즘 및 시간복잡도
 * N, M = 500
 * 빽드래킹으로 모든 경로 계산? 2^500 절대 불가능
 * DP로 각 길에 경로 개수를 기록해놓고 탐색 종료.
 * 
 * 3. 작업흐름
 * 
 * 재귀함수로 상하좌우 간다
 * 더 낮은 곳으로 이동
 * 만약 이미 경로개수가 기록되어 있다면 그거 이동하고 종료
 * 마지막에 dp[0][0] 리턴
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ1520 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N, M; //문제에서는 M을 세로라고 했지만, 여기서는 편의상 N을 세로 M을 가로로 지정함
	private static int[][] map;
	private static int[][] dp; //0으로 초기화
	private static boolean[][] isVisit;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};

	
	public static void main(String[] args) throws Exception {
		setInput();
		move(0, 0);
		System.out.println(dp[0][0]);
	}
	
	private static void setInput() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][M];
		isVisit = new boolean[N][M];
	}
	
	private static void move(int y, int x) {
		if (y == N-1 && x == M - 1) {
			dp[N-1][M-1] = 1;
			return;
		}
		
		for (int d=0; d<4; d++) {
			int nextY = y + dy[d];
			int nextX = x + dx[d];

			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) {
				continue;
			}
			if (map[y][x] <= map[nextY][nextX]) {
				continue;
			}
			
			if (dp[nextY][nextX] > 0) {
				dp[y][x] += dp[nextY][nextX];
				continue;
			}
			
			if (isVisit[nextY][nextX]) {
				continue;
			}

			
			move(nextY, nextX);
			dp[y][x] += dp[nextY][nextX];
			isVisit[nextY][nextX] = true;
		}
	}

}
