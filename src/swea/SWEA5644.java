package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 유저 A, B는 각각 (0, 0) (9, 9)에서 출발한다. 지도는 무조건 10 x 10 크기임
 * 둘은 이동해서 BC를 만나면 충전한다
 * BC가 겹쳐져 있으면 나누어 충전한다
 *

 * 
 * 
 * 2. 시간복잡도
 * 시뮬레이션 -> 그냥 맵을 돌면서 충전하는 로직실행 -> T * N*N
 * 
 * map에 비트마스킹으로 어느 BC에 속했는지 표시한다
 * 각 유저는 현재 속한 BC 중에 가장 큰 BC로 충전한다
 * 만약 둘이 고른 BC가 동일할 경우 
 * --> 각자가 속한 BC 리스트를 만든 뒤, 돌면서 최댓값을 구한다. 그 최댓값을 기준으로 충전한다. (한쪽은 포기하는 경우도 존재)
 * 
 * 3. 작업흐름
 * int M : 총 이동 시간
 * int A : BC의 개수
 * int[10][10] map
 * int ay, ax, by, bx : 각 유저의 위치
 * int totalEnergy
 * 
 * int[] bc : bc 의 성능이 들어있다
 * 
 * int[] dy = {0, -1, 0, 1, 0}
 * int[] dx = {0, 0, 1, 0, -1}
 * 
 * 
 * BC 를 맵에 설치한다. 좌표와 범위를 보고 해당 위치들을 표시한다 DFS 로직 사용
 * 
 * 
 * void installBC(int distance, int index, int y, int x)
 * if (distance == 0) return
 * 
 * for (d = 1; d<5; d++)
 *  범위 안에 있고, map[nextY][nextX] & (1 << index) == 0 이라면
 *  map[nextY][nextX] = map[nextY][nextX] | (1 << index)
 *  installBC(distance - 1, index, y + dy[d], x + dx[d])
 *  
 *  
 * void move()
 * 사용자 이동정보에 따라 이동한다
 * 
 * 해당 좌표의 BC에 따라 충전한다
 * List<Integer> aBC
 * List<Integer> bBC
 * 
 * for i 반복문으로
 * map[ay][ax] | (1 << i) > 0 이라면 aBC.add(i)
 * 
 * aBC, bBC 를 돌면서 둘의 인덱스가 다르면서 에너지를 최대로 얻는 값을 구하고, 그 값으로 충전한다 totalEnergy += 
 * 
 */



public class SWEA5644 {

	static class Point {
		int y;
		int x;
		int distance;

		public Point(int y, int x, int distance) {
			this.y = y;
			this.x = x;
			this.distance = distance;
		}
	}
	
	private static final Integer MAP_SIZE = 10;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int M; //총 이동시간
	private static int A; //BC의 개수
	private static int[][] map; // 맵이며, 무조건 크기는 10x10이다.
	private static int totalEnergy;
	private static int[] bc;
	private static int[] moveA;
	private static int[] moveB;
	
	private static int ay;
	private static int ax;
	private static int by;
	private static int bx;
	
	
	private static final int[] dy = {0, -1, 0, 1, 0};
	private static final int[] dx = {0, 0, 1, 0, -1};
    

	
    public static void main(String args[]) throws NumberFormatException, IOException{
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t=1; t<T+1; t++) {
    		run();
    		sb.append('#').append(t).append(' ').append(totalEnergy).append('\n');
    	}
    	System.out.println(sb);
    }
    
    private static void run() throws IOException {
    	getInput();
    	
    	charge(); //0초에 충전하고 시작한다
    	for (int time=0; time<M; time++) {
    		move(time);
    		charge();
    	}
    }
    
    private static void getInput() throws IOException {
    	st = new StringTokenizer(br.readLine());
    	M = Integer.parseInt(st.nextToken());
    	A = Integer.parseInt(st.nextToken());
    	
    	moveA = new int[M];
    	moveB = new int[M];
    	bc = new int[A];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int i=0; i<M; i++) {
    		moveA[i] = Integer.parseInt(st.nextToken());
    	}
    	st = new StringTokenizer(br.readLine());
    	for (int i=0; i<M; i++) {
    		moveB[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	
    	map = new int[MAP_SIZE][MAP_SIZE];
    	ay = 0;
    	ax = 0;
    	by = 9;
    	bx = 9;
    	
    	//BC 정보 저장
    	for (int i=0; i<A; i++) {
    		st = new StringTokenizer(br.readLine());
    		int bcX = Integer.parseInt(st.nextToken()) - 1;
    		int bcY = Integer.parseInt(st.nextToken()) - 1;
    		int bcC = Integer.parseInt(st.nextToken());
    		int bcP = Integer.parseInt(st.nextToken());
    		
    		bc[i] = bcP;
    		installBC(bcC, i, bcY, bcX);
    	}
    	
    	totalEnergy = 0;	
    }
    
    private static void installBC(int distance, int index, int y, int x) {
    	
    	Queue<Point> queue = new LinkedList<>();
    	queue.add(new Point(y, x, distance));
    	map[y][x] |= 1 << index;
    	
    	while (!queue.isEmpty()) {
    		Point nowPoint = queue.poll();
    		int nowDistance = nowPoint.distance;
    		int nowY = nowPoint.y;
    		int nowX = nowPoint.x;
    		
    		if (nowDistance == 0) {
    			continue;
    		}
    		
    		for (int d=1; d<5; d++) {
    			int nextY = nowY + dy[d];
    			int nextX = nowX + dx[d];
    			
    			if (nextY < 0 || nextY >= MAP_SIZE || nextX < 0 || nextX >= MAP_SIZE) {
        			continue;
        		}
        		if ((map[nextY][nextX] & (1 << index)) > 0) {
        			continue;
        		}
        		
        		map[nextY][nextX] |= 1 << index;
        		queue.add(new Point(nextY, nextX, nowDistance - 1));
    		}
    	}
    }
    
    private static void move(int time) {
    	ay += dy[moveA[time]];
    	ax += dx[moveA[time]];
    	by += dy[moveB[time]];
    	bx += dx[moveB[time]];
    }
    
    private static void charge() {
    	
    	boolean[] aBC = new boolean[A]; //현재 a 가 속해있는 BC의 배열 true
    	boolean[] bBC = new boolean[A]; //현재 b 가 속해있는 BC의 배열 true
    	
    	for (int i=0; i<A; i++) {
    		if ((map[ay][ax] & (1 << i)) > 0) {
    			aBC[i] = true;
    		}
    		if ((map[by][bx] & (1 << i)) > 0) {
    			bBC[i] = true;
    		}
    	}
    	
    	int maxEnergy = 0;
    	
    	for (int a=0; a<A; a++) {
    		for (int b=0; b<A; b++) {
    			int energyA = 0;
    			int energyB = 0;
    			if (aBC[a] == true) {
    				energyA = bc[a];
    			}
    			if (bBC[b] == true) {
    				energyB = bc[b];
    			}
    			
    			//둘이 같은 BC를 선택했다면 충전량이 절반이 된다
    			if (a == b && aBC[a] == true && bBC[b] == true) {
    				energyA /= 2;
    				energyB /= 2;
    			}
    			
    			maxEnergy = Math.max(maxEnergy, energyA + energyB);
    		}
    	}
    	
    	totalEnergy += maxEnergy;	
    }
    
}
