import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 아이디어
 * 정수 N을 입력받아 N 크기의 달팽이를 출력하라
 * 0,0 부터 1 -->
 * 
 * 2. 시간복잡도
 * 크기만큼이니까 N*N = 100
 * 
 * 3. 작업흐름
 *
 */

public class Solution {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<T+1; t++) {
			System.out.println("#" + t);
			makeSnail();
		}
	}
	
	public static void makeSnail() throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		int number = 1;
		int y = 0;
		int x = 0;
		map[0][0] = number++;
		
		while (number <= N*N) {
			while (x+1 < N && map[y][x+1] == 0) {
				x++;
				map[y][x] = number++;
			}
			
			while (y+1 < N && map[y+1][x] == 0) {
				y++;
				map[y][x] = number++;
			}
			
			while (x-1 >= 0 && map[y][x-1] == 0) {
				x--;
				map[y][x] = number++;
			}
			
			while (y-1 >= 0 && map[y-1][x] == 0) {
				y--;
				map[y][x] = number++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				sb.append(map[j][i]).append(' ');
			}
			if (j == N-1) {
				break;
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
}
