package algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2606 {
	
	private static int N;
	private static int edgeNumber;
	private static int[][] edges;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		edgeNumber = Integer.parseInt(st.nextToken());
		edges = new int[N+1][N+1];
		
		for (int i=0; i<edgeNumber; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			edges[from][to] = 1;
			edges[to][from] = 1;
		}
		
		System.out.println(bfs(1));
	}
	
	private static int bfs(int node) {
		
		int cnt = 0;
		
		boolean[] visited = new boolean[N+1];
		for (int i=0; i<N+1; i++) {
			visited[i] = false;
		}
		visited[0] = true;
		
		Queue q = new LinkedList();
		q.add(node);
		visited[node] = true;
		while(!q.isEmpty()) {
			int nowNode = (int) q.poll();
			for (int nextNode=1; nextNode<N+1; nextNode++) {
				if (edges[nowNode][nextNode] == 1 && visited[nextNode] == false) {
					q.add(nextNode);
					visited[nextNode] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}

}
