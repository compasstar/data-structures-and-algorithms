/**
 * 1. 아이디어
 * 젤다... 아니 링크는 (0,0) 에서 (N-1, N-1) 까지 이동해야 한다
 * 중간에 도둑루피를 지나면, 도둑루피의 크기만큼 소지금을 잃는다
 * 소지금을 최소로 하여 목적지까지 이동해야 한다
 * 
 * 2. 알고리즘 및 시간복잡도
 * 최소로 이동하여 목적지까지 가야 한다.
 * 최소로 이동하려면 왔던 곳을 다시 가지는 않는다.
 * 
 * 1) 백트래킹
 * 방향^맵크기 = 4^(125^2) 불가능
 * 
 * 2) BFS(다익스트라)를 통해 맵을 한바퀴 돈다
 * 시간복잡도 = 맵의 크기 * log(Edge) = N*N * log(4*N*N) = 125^2 * log(4*125^2) ==> 가능
 * 
 * 3. 작업흐름
 * class Node
 * 
 * while !pq.isEmpty
 * 	pq.poll
 * 	방문처리
 * 	거리가 더 짧다면 업데이트한다
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int y;
	int x;
	int w;
	
	public Node(int y, int x, int w) {
		this.y = y;
		this.x = x;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.w - o.w;
	}
	
}


public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int[][] map;
	private static int[][] distance;
	private static int problemCount = 1;
	
	private static final int[] dy = {1, 0, -1, 0};
	private static final int[] dx = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		run();
		System.out.println(sb);
	}
	
	private static void run() throws NumberFormatException, IOException {
		try {
			while (true) {
				setInput(); // N==0 이면 에러 발생
				getMinDistanceAndSetAnswer();
			}
		} catch (IllegalArgumentException e) {
			return;
		}	
	}
	
	
	private static void getMinDistanceAndSetAnswer() {
		int minDistance = getMinDistance();
		sb.append("Problem ").append(problemCount++).append(": ").append(minDistance).append('\n');
	}
	
	
	
	private static void setInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		if (N == 0) {
			throw new IllegalArgumentException();
		}
		
		map = new int[N][N];
		distance = new int[N][N];
		
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				distance[j][i] = Integer.MAX_VALUE;
			}
		}
	}
	
	private static int getMinDistance() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, map[0][0]));
		distance[0][0] = map[0][0];
		
		while (!pq.isEmpty()) {
			Node nowNode = pq.poll();
			
			for (int d=0; d<4; d++) {
				int nextY = nowNode.y + dy[d];
				int nextX = nowNode.x + dx[d];
				
				if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) {
					continue;
				}
				if (distance[nextY][nextX] != Integer.MAX_VALUE) {
					continue;
				}
				
				if (distance[nextY][nextX] > distance[nowNode.y][nowNode.x] + map[nextY][nextX]) {
					distance[nextY][nextX] = distance[nowNode.y][nowNode.x] + map[nextY][nextX];
					pq.add(new Node(nextY, nextX, distance[nextY][nextX]));
				}
			}
		}
		
		return distance[N-1][N-1];
	}

}
