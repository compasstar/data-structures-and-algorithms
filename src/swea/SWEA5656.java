package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * N: 구슬 쏘는 횟수
 * map: H * W (행렬)
 * 0: 빈공간, 1~9: 벽돌
 * 구슬은 좌우로만 움직이며 가장 위의 벽돌을 깨뜨린다
 * 구슬이 명중하면 마치 폭탄처럼 상하좌우가 벽돌의 숫자만큼 같이 사라진다 -> 연쇄폭발 가능!
 * 빈 공간이 있을 경우 벽돌은 밑으로 떨어진다
 * 
 * 구슬을 떨어뜨릴 때 최대한 많은 벽돌을 제거하고, 남은 벽돌의 개수를 구하시오!
 * 
 * 
 * 2. 알고리즘 및 시간복잡도
 * 모든 열에 대하여 N번 공을 전부 떨어뜨려 보고 최댓값을 구한다
 * 시간복잡도 -> W^N * (공1개시간) = 12^4 * (공1개시간) = 20,736 * (공1개시간)
 * 공 1개 떨어뜨릴 때 걸리는 시간 = 맵의 크기 = H * W = 15 * 12 = 180
 * 총 걸리는 시간 = 20736 * 180 = 3,732,480 (통과)
 * 
 * 3. 작업흐름
 * 
 * class Brick
 * 	int y, int x, int range
 * 
 * 
 * 
 * static int totakBrick;
 * static int minRemainBrick = Integer.MAX_VALUE
 * 
 * main {
 * 	game(0, 0, map);
 * 	System.out.println(minRemainBrick);
 * }
 * 
 * void game(int count, int brokenBrick, int[][] map) : drop을 모든 장소에서 N번 하면서 minRemainBrick 을 업데이트 한다 (DFS)
 * 		if (count == N) 
 * 			minRemainBrick = Math.min(minRemainBrick, totalBrick - brokenBrick)
 * 			return;
 * 
 * for (int i=0; i<W; i++) {
 * 	int[][] copiedMap = copyMap(map); //맵 만들고 새로운 걸로 재귀 돌기
 * 	int broke = drop(i, copiedMap)
 * 	run(count + 1, brokenBrick + broke, copiedMap)
 * }
 * 
 * int drop(int w, int[][] map) { : 부순 벽돌 개수 return
 * 
 * 		공이 떨어져서 부딪히는 벽돌을 Queue에 넣는다
 * 
 * 		//벽돌 폭발
 * 		while(!queue.isEmpty()
 * 			벽돌 = queue.poll()
 * 			벽돌 폭발: 맵 돌면서 같이 폭발한 벽돌을 queue에 삽입, 맵에서 0으로 바꿔주기
 * 
 * 		//빈공간 떨어뜨리기
 * 		// column 마다 위쪽으로 순회하면서 벽돌들을 저장하고, 아래부터 다시 초기화  위는 0으로 초기화ㅣ
 * 	
 * 
 * }
 * 
 * int[][] copyMap() {
 * 	return copiedMap;
 * }
 * 
 * 
 */



public class SWEA5656 {

	static class Brick {
		int y;
		int x;
		int range;

		public Brick(int y, int x, int range) {
			this.y = y;
			this.x = x;
			this.range = range;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N, W, H;
	private static int[][] originMap;
	
	private static int totalBrickAmount;
	private static int minRemainBrickAmount;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<T+1; t++) {
			int answer = run();
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int run() throws IOException {
		setInput();
		game(0, 0, originMap);
		return minRemainBrickAmount;
	}
	
	private static void setInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		originMap = new int[H][W];
		
		totalBrickAmount = 0;
		for (int j=0; j<H; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<W; i++) {
				originMap[j][i] = Integer.parseInt(st.nextToken());
				if (originMap[j][i] != 0) {
					totalBrickAmount++;
				}
			}
		}
	
		minRemainBrickAmount = Integer.MAX_VALUE;
	}
	
	private static void game(int count, int brokenBrick, int[][] map) {
		if (count == N) {
			minRemainBrickAmount = Math.min(minRemainBrickAmount, totalBrickAmount - brokenBrick);
			return;
		}
		
		for (int i=0; i<W; i++) {
			int[][] copiedMap = copyMap(map);
			int broke = drop(i, copiedMap);
			game(count + 1, brokenBrick + broke, copiedMap);
		}
	}
	
	private static int drop(int w, int[][] map) {
		int brokenBrickAmount = 0;
		
		Queue<Brick> queue = new LinkedList<>();
		for (int j=0; j<H; j++) {
			if (map[j][w] != 0) {
				queue.add(new Brick(j, w, map[j][w]));
				map[j][w] = 0;
				brokenBrickAmount++;
				break;
			}
		}
		
		//벽돌 폴발
		while (!queue.isEmpty()) {
			Brick brick = queue.poll();
			
			//y축 폭발
			for (int j=(-1) * (brick.range - 1); j<brick.range; j++) {
				if (brick.y + j < 0 || brick.y + j >= H || map[brick.y + j][brick.x] == 0) {
					continue;
				}
				queue.add(new Brick(brick.y + j, brick.x, map[brick.y + j][brick.x]));
				map[brick.y + j][brick.x] = 0;
				brokenBrickAmount++;
			}
			//x축 폭발
			for (int i=(-1) * (brick.range - 1); i<brick.range; i++) {
				if (brick.x + i < 0 || brick.x + i >= W || map[brick.y][brick.x + i] == 0) {
					continue;
				}
				queue.add(new Brick(brick.y, brick.x + i, map[brick.y][brick.x + i]));
				map[brick.y][brick.x + i] = 0; 
				brokenBrickAmount++;
			}
		}
		
		
		//빈 공간 떨어뜨리기
		for (int i=0; i<W; i++) {
			Queue<Integer> column = new LinkedList<>();
			for (int j=H-1; j>=0; j--) {
				if (map[j][i] == 0) {
					continue;
				}
				column.add(map[j][i]);
			}
			
			for (int j=H-1; j>=0; j--) {
				if (column.isEmpty()) {
					map[j][i] = 0;
				} else {
					map[j][i] = column.poll();
				}
			}
		}
		
		return brokenBrickAmount;
	}
	
	private static int[][] copyMap(int[][] map) {
		int[][] copiedMap = new int[H][W];
		for (int j=0; j<H; j++) {
			for (int i=0; i<W; i++) {
				copiedMap[j][i] = map[j][i];
			}
		}
		return copiedMap;
	}
	




}
