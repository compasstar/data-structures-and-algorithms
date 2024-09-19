package algorithm.topologicalsort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ2252 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static List<Integer>[] graph;
	private static int[] indegree; //진입 0인 노드들 저장
	
	private static int N;
	private static int M;
	


	public static void main(String[] args) throws IOException {
		getInput();	
		
		topologicalSort();

	}
	
	private static void getInput() throws NumberFormatException, IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N+1];
		graph = new ArrayList[N+1];
		for (int i=1; i< N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph[from].add(to);
			indegree[to]++;
		}
	}
	
	private static void topologicalSort() {
		
		Queue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i < N+1; i++) {
			if (indegree[i] == 0) {
				pq.offer(i);
			}
		}
		
		while (!pq.isEmpty()) {
			int indegreeZero = pq.poll();	
			System.out.print(indegreeZero + " ");
			
			List<Integer> vertex = graph[indegreeZero];
			
			for (int i : vertex) {
				indegree[i]--;
				
				if (indegree[i] == 0) {
					pq.offer(i);
				}
			}
		}
	}
	
}
