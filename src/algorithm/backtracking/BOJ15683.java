package algorithm.backtracking; /**
 * 1. 아이디어
 * N * M 맵
 * CCTV 종류 5개. 벽 없으면 일직선으로 쭉 쏠 수 있다. CCTV는 통과해서 볼 수 있음
 * CCTV 회전가능. (90도)
 * 0 빈칸, 1~5 CCTV, 6 벽
 * 사각지대의 최소 크기를 구하시오!!!
 * 
 * 2. 알고리즘
 * N, M = 8
 * CCTV 개수 = 8
 * 
 * 각 CCTV를 돌리면서 맵을 칠한다. 사각지대 최소를 구한다
 * 
 * 완전탐색: 4방향 ^ CCTV 개수 = 65,536
 * 
 * 
 * 3. 작업흐름
 * 
 * static int N, M, cctvNum
 * static int minBlindSpot
 * 
 * List<algorithm.bfs.algorithm.backtracking.Point> cctvs :  cctv좌표
 * 
 * void dfs(int cctvIndex, int[][] map)
 * if cctvIndex == cctvNum
 *  Math.min ( minBlindSpot, getBlindSpot())
 *  return
 *  
 *  for 4방향
 *  	cctvs.get(cctvIndex) 으로 부터 방향에 맞추어 쏜다. 맵을 -1으로 바꾼다
 * 		깊은 복사 해서 넘긴다
 * 		dfs(cctvIndex + 1, 복사된 map)
 *  
 *  
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15683 {

	static class Point {
		int y;
		int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class Cctv {
		int y;
		int x;
		int type;

		public Cctv(int y, int x, int type) {
			this.y = y;
			this.x = x;
			this.type = type;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int M;
	private static int minBlindSpot;
	private static int[][] map;
	private static List<Cctv> cctvs;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};
	private static final int[][][] types = { 
			{}, //0번 인덱스는 비워둔다
			{{0}, {1}, {2}, {3}}, 
			{{0, 2}, {1, 3}}, 
			{{0, 1}, {1, 2}, {2, 3}, {3, 0}}, 
			{{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}, 
			{{0, 1, 2, 3}} 
			};
	

	public static void main(String[] args) throws IOException  {
		
		getInput();
		
		dfs(0, map);
		
		System.out.println(minBlindSpot);

	}
	
	private static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cctvs = new ArrayList<>();
		
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
				if (map[j][i] >= 1 && map[j][i] <= 5) {
					cctvs.add(new Cctv(j, i, map[j][i]));
				}
				
			}
		}
		
		minBlindSpot = Integer.MAX_VALUE;
	}
	
	private static void dfs(int cctvIndex, int[][] map) {
		
		if (cctvIndex == cctvs.size()) {
			minBlindSpot = Math.min(minBlindSpot, getBlindSpot(map));
			return;
		}
		Cctv cctv = cctvs.get(cctvIndex);
		
		
		for (int[] direction : types[cctv.type]) {
			int[][] tempMap = copyMap(map);

			for (int d : direction) {
				int nowY = cctv.y;
				int nowX = cctv.x;
				
				while (true) {
					int nextY = nowY + dy[d];
					int nextX = nowX + dx[d];
					
					if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) {
						break;
					}
					if (tempMap[nextY][nextX] == 6) {
						break;
					}
					
					if (tempMap[nextY][nextX] == 0) {
						tempMap[nextY][nextX] = -1;
					}
					
					nowY = nextY;
					nowX = nextX;
				}
			}
			
			dfs(cctvIndex + 1, tempMap);
		}
	}
	
	private static int getBlindSpot(int[][] map) {
		int cnt = 0;
		for (int j=0; j<N; j++) {
			for (int i=0; i<M; i++) {
				if (map[j][i] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static int[][] copyMap(int[][] map) {
		int[][] copyMap = new int[N][M];
		for (int j=0; j<N; j++) {
			for (int i=0; i<M; i++) {
				copyMap[j][i] = map[j][i];
			}
		}
		return copyMap;
	}

}
