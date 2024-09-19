package algorithm.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 모든 컴퓨터가 연결되어야 하는데 비용이 최소로 --> 최소스패닝트리
 * 
 * 2. 시간복잡도
 * algorithm.binarysearch.Edge 추가 M
 * 힙에 추가하고 빼고 logN
 * MlogN = 100000 * log1000
 * 
 * 3. 작업흐름
 * class algorithm.binarysearch.Edge
 * union find parent
 * edges poll 하면서 비용계산
 */



public class BOJ1922 {

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
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}


	private static int[] parent;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int N;
	private static int M;
	
	private static final PriorityQueue<Edge> edges = new PriorityQueue<>();

	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		for (int i=1; i<N+1; i++) {
			parent[i] = i;
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.add(new Edge(a, b, c));
		}
		
		int totalWeight = 0;
		while (!edges.isEmpty()) {
			Edge nowEdge = edges.poll();
			if (find(nowEdge.from) == find(nowEdge.to)) {
				continue;
			}
			union(nowEdge.from, nowEdge.to);
			totalWeight += nowEdge.weight;
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

