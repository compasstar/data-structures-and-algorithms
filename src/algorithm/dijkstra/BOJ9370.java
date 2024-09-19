package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * S에서 출발, 목적지까지 최단거리로 도착!
 * 회색원: 출발
 * 점선: 꼭 지나야하는 도로
 * 검은원: 목적지
 * N, M, T : 교차로, 도로, 목적지
 * S, G, H : 출발지, 꼭 지나야하는 도로
 * 목적지까지 도달할 때 걸리는 거리를 오름차순으로 출력하시오!
 * 
 * 2. 알고리즘
 * 그래프이론 -> 한 점에서 목적지까지 최단거리 -> 다익스트라
 * O(algorithm.backtracking.Node + algorithm.binarysearch.Edge) = 2000 + 50000
 * 테스트케이스 = 100
 * 총시간 = 100 * 50000 = 5,000,000 가능!
 * 
 * 3. 작업흐름
 * class algorithm.backtracking.Node Comparable
 * 노드번호
 * 누적거리
 * 
 * visited[node][g, h 교차로 지났는지 확인] = 여태 지나온 거리
 * PriorityQueue 목적지도착큐
 * 
 * queue = PriorityQueue
 * while(!queue.isEmpty) {
 * 	node = queue.poll
 * 	nextNode 가 방문하지 않았더라면
 * 	queue.add(nextNode), 방문처리
 * 
 * 	만약 nextNode가 목적지이며, 방문하지 않았더라면 목적지도착큐에 add.
 * }
 * 
 * 목적지도착큐에서 pop하면서 오름차순으로 거리 출력
 *
 */


public class BOJ9370 {


	static class Node implements Comparable<Node> {
		int id;
		int distance;

		public Node(int id, int distance) {
			this.id = id;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}

	static class Edge {
		int from;
		int to;
		int distance;

		public Edge(int from, int to, int distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N, M, T; //노드, 엣지, 목적지 개수
	private static int S, G, H; //출발지, 지나야하는 엣지
	
	private static List<Edge>[] graph;
	private static List<Integer> goals; //도착지 후보들
	private static Queue<Integer> arrivalNodes; //교차로 지나서 도착한 노드들

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int testCase = Integer.parseInt(br.readLine());
		for (int t=0; t<testCase; t++) {
			run();
		}
		System.out.println(sb);
		
	}
	
	private static void run() throws IOException {
		setInput();
		
		for (int goal : goals) {
			long route1 = getMinDistance(S, G) + getMinDistance(G, H) + getMinDistance(H, goal);
			long route2 = getMinDistance(S, H) + getMinDistance(H, G) + getMinDistance(G, goal);
			long route3 = getMinDistance(S, goal);
			
			if (route3 == Math.min(route1, route2)) {
				arrivalNodes.add(goal);
			}
		}
		
		while (!arrivalNodes.isEmpty()) {
			int node = arrivalNodes.poll();
			sb.append(node).append(' ');
		}
		
		sb.append('\n');
	}
	
	private static void setInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		goals = new ArrayList<>();
		arrivalNodes = new PriorityQueue<>();
		
		for (int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[a].add(new Edge(a, b, d));
			graph[b].add(new Edge(b, a, d));
		}
		
		for (int i=0; i<T; i++) {
			goals.add(Integer.parseInt(br.readLine()));
		}
	}
	
	private static long getMinDistance(int start, int end) {

		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!queue.isEmpty()) {
			Node nowNode = queue.poll();
			
			if (visited[nowNode.id]) {
				continue;
			}
			visited[nowNode.id] = true;

			//다음 엣지 이어주기
			for (Edge edge : graph[nowNode.id]) {
				int distance = nowNode.distance + edge.distance;
				if (dist[edge.to] > distance) {
					dist[edge.to] = distance;
					queue.add(new Node(edge.to, distance));
				}
			}
		}
		
		return dist[end];
	}
}
