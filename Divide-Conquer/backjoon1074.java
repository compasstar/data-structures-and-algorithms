import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int R;
	private static int C;
	
	private static int[][] map = {{0, 1}, {2, 3}};

	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.println(divideConcur(N, R, C));
	}
	
	private static int divideConcur(int n, int y, int x) {
		if (n == 1) {
			return map[y][x];
		}
		
		int half = 1 << (n-1);
		
		if (y < half && x < half) {
			return divideConcur(n-1, y, x);
		} else if (y < half && x >= half) {
			return half * half + divideConcur(n-1, y, x - half);
		} else if (y >= half && x < half) {
			return half * half * 2 + divideConcur(n-1, y - half, x);
		} else if (y >= half && x >= half) {
			return half * half * 3 + divideConcur(n-1, y - half, x - half);
		}
	
		return -1;
	}
	

}
