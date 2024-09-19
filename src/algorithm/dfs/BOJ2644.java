package algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2644 {
	
	private static int N;
	private static int M;
	private static int man1;
	private static int man2;
	private static int[][] family;
	private static boolean[] visited;
	private static boolean sw = false;
	private static int answer = -1;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		man1 = Integer.parseInt(st.nextToken());
		man2 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		family = new int[N+1][N+1];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			family[first][second] = 1;
			family[second][first] = 1;
		}
		
		visited = new boolean[N+1];
		visited[man1] = true;
		
		dfs(man1, man2, 0);
		System.out.println(answer);
	}
	
	private static void dfs(int now, int goal, int distance) {
		if (goal == now) {
			answer = distance;
			return;
		}
		
		for (int next=1; next<N+1; next++) {
			if (family[now][next] == 1 && visited[next] == false) {
				visited[next] = true;
				dfs(next, goal, distance + 1);
			}
		}
	}
}
