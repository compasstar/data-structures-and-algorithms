import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N, M;
	private static boolean[][] graph;


	public static void main(String[] args) throws NumberFormatException, IOException  {
		
		setInput();
		
		for (int k=1; k<N+1; k++) {
			for (int j=1; j<N+1; j++) {
				for (int i=1; i<N+1; i++) {
					if (graph[j][k] && graph[k][i]) {
						graph[j][i] = true;
					}
				}
			}
		}
		
		int answer = 0;
		for (int j=1; j<N+1; j++) {
			int cnt = 0;
			for (int i=1; i<N+1; i++) {
				if (graph[j][i]) {
					cnt++;
				} else if (graph[i][j]) {
					cnt++;
				}
			}

			if (cnt == N) {
				answer++;
			}
		}
		System.out.println(answer);
	}
	
	private static void setInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new boolean[N+1][N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = true;
		}
		
		for (int i=1; i<N+1; i++) {
			graph[i][i] = true;
		}
	}

}
