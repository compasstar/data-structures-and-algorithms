package algorithm.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * N개의 논
 * 직접 논에 우물 파기 or 다른 논에서 물 끌어오기
 * 우물 파는 비용 / 물을 끌어오는 비용
 * 최소 비용으로 모든 논에 물을 대거라!
 * 
 * 2. 알고리즘
 * 최소비용으로 모든 논을 연결한다 -> MST
 * V+E = 300 + 300^2
 * 
 * 우물을 만들고, 우물을 모든 논에 연결한다.
 * 우물을 포함하여 모든 논이 연결되도록 최소비용트리를 만든다
 * 
 * 
 * 3. 작업흐름
 * 
 * class algorithm.binarysearch.Edge
 * 
 * union, find
 * int parents[N+1] : 0번인덱스는 우물이다
 * 
 * 모든 Edge를 받으면서 PQ에 넣는다. Priority Queue 는 코스트로 분류가 되어있다
 * 각 큐에서 빼면서 union을 통해 모든 노드를 이어준다
 *
 */




public class BOJ1368 {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int w;

		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static PriorityQueue<Edge> edges;

	private static int parents[];
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		setInput();
		System.out.println(getMinCost());
	}
	
	private static void setInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		edges = new PriorityQueue<>();
		
		parents = new int[N+1]; //0번 인덱스는 우물
		for (int i=0; i<N+1; i++) {
			parents[i] = i;
		}
		
		//우물이랑 각 논이랑 연결
		for (int i=1; i<N+1; i++) {
			int w = Integer.parseInt(br.readLine());
			edges.add(new Edge(0, i, w));
		}
		
		for (int j=1; j<N+1; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<N+1; i++) {
				int w = Integer.parseInt(st.nextToken());
				edges.add(new Edge(j, i, w));
			}
		}
	}
	
	private static int getMinCost() {
		int totalCost = 0;
		
		while (!edges.isEmpty()) {
			Edge nowEdge = edges.poll();
			if (isSameTree(nowEdge.from, nowEdge.to)) {
				continue;
			}
			union(nowEdge.from, nowEdge.to);
			totalCost += nowEdge.w;
		}
		
		return totalCost;
	}
	
	private static int find(int x) {
		if (parents[x] == x) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}
	
	private static void union(int y, int x) {
		y = find(y);
		x = find(x);
		
		if (y < x) {
			parents[x] = y;
		} else if (y > x) {
			parents[y] = x;
		}
	}
	
	private static boolean isSameTree(int y, int x) {
		return find(y) == find(x);
	}

}
