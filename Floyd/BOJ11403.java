import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int[][] graph;


	public static void main(String[] args) throws Exception  {
		setInput();
		floyd();
		printGraph();
	}

	private static void setInput() throws Exception {
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		for (int j = 0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				graph[j][i] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static void floyd() {
		for (int k=0; k<N; k++) {
			for (int j=0; j<N; j++) {
				for (int i=0; i<N; i++) {
					if (graph[j][i] == 0) {
						if (graph[j][k] == 1 & graph[k][i] == 1) {
							graph[j][i] = 1;
						}
					}
				}
			}
		}
	}
	
	private static void printGraph() {
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				sb.append(graph[j][i]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
