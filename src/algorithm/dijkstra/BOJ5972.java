package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ5972 {

	static class Node implements Comparable<Node> {
		int id;
		int w;

		public Node(int id, int w) {
			this.id = id;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	private static int N, M;
	private static List<Node>[] graph;
	
	private static final int INF = Integer.MAX_VALUE;


	public static void main(String[] args) throws NumberFormatException, IOException  {
		setInput();
		int answer = getMinDistance(1, N);
		System.out.println(answer);
	}
	
	private static void setInput() throws IOException {
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
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}
	}
	
	private static int getMinDistance(int from, int to) {
		
		boolean[] isVisit = new boolean[N+1];
		int[] distance = new int[N+1];
		for (int i=0; i<N+1; i++) {
			distance[i] = INF;
		}
		distance[from] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(from, 0));
		
		while (!pq.isEmpty()) {
			Node nowNode = pq.poll();
			if (isVisit[nowNode.id]) {
				continue;
			}
			isVisit[nowNode.id] = true;
			
			for (Node nextNode : graph[nowNode.id]) {
				if (distance[nextNode.id] > distance[nowNode.id] + nextNode.w) {
					distance[nextNode.id] = distance[nowNode.id] + nextNode.w;
					pq.add(new Node(nextNode.id, distance[nextNode.id]));
				}
			}
		}
		
		return distance[to];
	}

}
