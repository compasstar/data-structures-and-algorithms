package algorithm.mst;
/**
 * 1. 아이디어
 * N개의 컴퓨터를 모두 연결한다.
 * 최대한 랜선을 짧게 연결하고 나머지는 기부하라.
 * 
 * 2. 알고리즘 및 시간복잡도
 * 모든 노드 최소 연결 -> MST
 * 시간복잡도: O(ElogE) = O(N^2logN^2) = 2500 * log(2500)
 * 
 * 
 * 3. 작업흐름
 * parent, union, find
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;



public class BOJ1414 {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
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
	
	private static int N;
	private static int totalLength;
	
	private static int[] parents;
	private static PriorityQueue<Edge> pq;

	public static void main(String[] args) throws IOException, Exception {
		setInput();
		int answer = getMaxLength();
		if (!isAllConnected()) {
			System.out.println(-1);
			return;
		}
		System.out.println(answer);
	}
	
	private static void setInput() throws Exception {
		N = Integer.parseInt(br.readLine());
		totalLength = 0;
		pq = new PriorityQueue<>();
		
		for (int j=0; j<N; j++) {
			char[] line = br.readLine().toCharArray();
			for (int i=0; i<N; i++) {
				int l = 0;
				if (line[i] >= 'A' && line[i] <= 'Z') {
					l = line[i] - 'A' + 27;
					pq.add(new Edge(j, i, l));
				} else if (line[i] >= 'a' && line[i] <= 'z') {
					l = line[i] - 'a' + 1;
					pq.add(new Edge(j, i, l));
				}
				totalLength += l;
			}
		}
		
		parents = new int[N];
		for (int i=0; i<N; i++) {
			parents[i] = i;
		}
	}
	
	private static int getMaxLength() {
		int usedLength = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (isSibling(edge.from, edge.to)) {
				continue;
			}
			union(edge.from, edge.to);
			usedLength += edge.weight;
		}
		return totalLength - usedLength;
	}
	
	private static int find(int x) {
		if (parents[x] == x) {
			return x;
		}
		return find(parents[x]);
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
	
	private static boolean isSibling(int y, int x) {
		if (find(y) == find(x)) {
			return true;
		}
		return false;
	}
	
	private static boolean isAllConnected() {
		int p = find(parents[0]);
		for (int i=0; i<N; i++) {
			if (p != find(parents[i])) {
				return false;
			}
		}
		return true;
	}

}
