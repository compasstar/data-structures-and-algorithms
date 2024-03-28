import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();

	private static int N, M; //노드, 간선
	private static int[][] graph;
	
	private static final int INF = 10000000;
	

	public static void main(String[] args) throws Exception  {
		setInput();
		floyd();
		printGraph();
	}
	
	private static void setInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new int[N+1][N+1];
		for (int j=0; j<N+1; j++) {
			for (int i=0; i<N+1; i++) {
				graph[j][i] = INF;
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from][to] = Math.min(graph[from][to], weight);
		}
	}
	
	private static void floyd() {
		for (int k=1; k<N+1; k++) {
			for (int j=1; j<N+1; j++) {
				for (int i=1; i<N+1; i++) {
					graph[j][i] = Math.min(graph[j][k] + graph[k][i], graph[j][i]);
				}
			}
		}
		
		for (int j=1; j<N+1; j++) {
			for (int i=1; i<N+1; i++) {
				if (graph[j][i] == INF || j == i) {
					graph[j][i] = 0;
				}
			}
		}
	}
	
	private static void printGraph() {
		for (int j=1; j<N+1; j++) {
			for (int i=1; i<N+1; i++) {
				sb.append(graph[j][i]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
