package algorithm.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 1. 아이디어
 * 최소 스패닝 트리 구하기
 * 그래프의 모든 정점들을 가중치 최소로 연결하기
 * 힙에 시작노드를 넣는다
 * 힙이 빌 때까지 : 힙에서 pop, 해당 노드 방문 안했다면 방문표시, 비용추가하고, 연결된 간선들 힙에 넣는다
 */



public class BOJ1197 {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge (int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int V;
	private static int E;
	private static final PriorityQueue<Edge> edges = new PriorityQueue<>();
	
	private static int[] parent;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[V+1];
		
		for (int i=1; i<V+1; i++) {
			parent[i] = i;
		}
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges.add(new Edge(from, to, weight));
		}
		
		int totalWeight = 0;
		while (!edges.isEmpty()) {
			Edge nowEdge = edges.poll();
			if (find(nowEdge.from) == find(nowEdge.to)) {
				continue;
			} else {
				union(nowEdge.from, nowEdge.to);
				totalWeight += nowEdge.weight;
			}
		}
		
		System.out.println(totalWeight);
	}
	
	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return find(parent[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x < y) {
			parent[y] = x;
		} else if (x > y) {
			parent[x] = y;
		}
	}
}

