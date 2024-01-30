import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int N;
	private static int[][] city;
	private static boolean[] visited;
	
	private static int minPrice = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		city = new int[N][N];
		visited = new boolean[N];
		
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				city[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		
		for (int i=0; i<N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(1, i, i, 0);
		}
		
		System.out.println(minPrice);

	}
	
	private static void dfs(int count, int now, int origin, int price) {
		if (count == N) {
			if (city[now][origin] == 0) {
				return;
			}
			price += city[now][origin];
			minPrice = Math.min(price, minPrice);
			return;
		}
		
		for (int i=0; i<N; i++) {
			if (visited[i] || city[now][i] == 0) {
				continue;
			}
			visited[i] = true;
			dfs(count + 1, i, origin, price + city[now][i]);
			visited[i] = false;
		}
		
	}

}
