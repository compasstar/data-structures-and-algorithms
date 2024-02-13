import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int N;
	private static int[][] board;
	private static int total;
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		total = 0;
		
		recur(0);
		
		System.out.println(total);
		
	}
	
	private static void recur(int column) {
		if (column == N) {
			total += 1;		
			return;
		}
		
		
		for (int row=0; row<N; row++) {			
			if (board[row][column] > 0) {
				continue;
			}

			for (int i=0; i<N - column; i++) {
				board[row][column + i] += 1;
				if (row + i < N) {
					board[row + i][column + i] += 1;
				}
				if (row - i >= 0) {
					board[row - i][column + i] += 1;
				}
			}
			recur(column + 1);
			for (int i=0; i<N - column; i++) {
				board[row][column + i] -= 1;
				if (row + i < N) {
					board[row + i][column + i] -= 1;
				}
				if (row - i >= 0) {
					board[row - i][column + i] -= 1;
				}
			}

		}
		
	}

}
