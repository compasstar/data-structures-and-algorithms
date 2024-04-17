import java.io.*;
import java.util.*;

/**
 * 1. 아이디어
 * N개의 섬
 * 섬 사이에는 다리가 설치 되어 있음
 * 다리마다 중량제한이 존재
 * 공장이 두 개 있는데, 한 공장에서 다른 공장으로 물품들을 옮겨야 한다.
 * 한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 구하시오
 * 
 * 2. 알고리즘 및 시간복잡도
 * N: 섬 개수
 * M: 다리 개수 -> from, to, 중량제한
 * 두 섬 사이에 여러개 다리 있을 수 있음. 무조건 양방향.
 * 
 * 빨리가는 건 상관 없다. 그냥 갈 수 있냐 없냐만 따지면 됨.
 * 물건 무게를 정해서 BFS를 통해 가능한지 체크
 * 물건 무게 정하기 -> 이분탐색 O(logC) = O(log1,000,000,000) = O(30)
 * 길 가능한지 탐색 -> BFS O(N) = O(10,000)
 * 
 * 
 * 3. 작업흐름
 * 
 * 이분탐색 메서드
 * check(bfs)
 * 
 * 
 */

class Edge implements Comparable<Edge> {
	int from;
	int to;
	int weight;
	
	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public int compareTo(Edge o) {
		return this.weight - o.weight;
	}
}

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N, M;
	private static List<Edge>[] graph;
	private static int g1, g2;
	
	
	public static void main(String[] args) throws Exception {
		setInput();
		int answer = getMaxWeight(g1, g2);
		System.out.println(answer);
	}
	
	private static void setInput() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for (int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(a, b, c));
			graph[b].add(new Edge(b, a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		g1 = Integer.parseInt(st.nextToken());
		g2 = Integer.parseInt(st.nextToken());
	}
	
	private static int getMaxWeight(int from, int to) {
		int start = 0;
		int end = 1000000001;
		
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if (isAbleToMove(from, to, mid)) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return end - 1;
	}
	
	private static boolean isAbleToMove(int from, int to, int weight) {
		boolean[] isVisit = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(from);
		isVisit[from] = true;
		
		while (!queue.isEmpty()) {
			int nowNode = queue.poll();
			
			for (Edge edge : graph[nowNode]) {
				if (isVisit[edge.to]) {
					continue;
				}
				if (edge.weight < weight) {
					continue;
				}
				queue.add(edge.to);
				isVisit[edge.to] = true;
				
				if (edge.to == to) {
					return true;
				}
			}
		}
		return false;
	}
}
