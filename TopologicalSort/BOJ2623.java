import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 가수 출연 순서 구하기
 * 순서 정하는 것이 불가능할 수도 있음!
 * 위상정렬
 */

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int M;
	private static List<Integer>[] graph;
	private static int[] indegree;



	public static void main(String[] args) throws IOException  {

		getInput();
		
		topologicalSort();
		
		if (!check()) {
			System.out.println(0);
			return;
		}
		
		System.out.println(sb);
	}
	
	private static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		indegree = new int[N+1];
		
		for (int i=1; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			int from = Integer.parseInt(st.nextToken());
			for (int n=0; n<num-1; n++) {
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				indegree[to]++;
				from = to;
			}
		}
	}
	
	private static void topologicalSort() {
		
		Queue<Integer> queue = new PriorityQueue<>();
		
		for (int i=1; i<N+1; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			sb.append(node).append('\n');
			
			List<Integer> temp = graph[node];
			
			for (int child : temp) {
				indegree[child]--;
				if (indegree[child] == 0) {
					queue.offer(child);
				}
			}
	
		}
	}
	
	private static boolean check() {
		for (int i=1; i<N+1; i++) {
			if (indegree[i] > 0) {
				return false;
			}
		}
		
		return true;
	}
}
