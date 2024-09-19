package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SWEA4008 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int[] numbers;
	private static int[] operators; // +, -, *, /
	private static int maxValue;
	private static int minValue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i<T+1; i++) {
			sb.append('#').append(i).append(' ').append(run()).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int run() throws NumberFormatException, IOException {
		
		getInput();
		
		dfs(numbers[0], 1, operators[0], operators[1], operators[2], operators[3]);
		
		return maxValue - minValue;
	}
	
	private static void getInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		minValue = 100000000;
		maxValue = -100000000;
	}
	
	private static void dfs(int result, int index, int op1, int op2, int op3, int op4) {
		
		if (op1 == 0 && op2 == 0 && op3 == 0 && op4 == 0) {
			maxValue = Math.max(maxValue, result);
			minValue = Math.min(minValue, result);
		}
		
		if (op1 > 0) {
			dfs(result + numbers[index], index + 1, op1 - 1, op2, op3, op4);
		}
		if (op2 > 0) {
			dfs(result - numbers[index], index + 1, op1, op2 - 1, op3, op4);
		}
		if (op3 > 0) {
			dfs(result * numbers[index], index + 1, op1, op2, op3 - 1, op4);
		}
		if (op4 > 0) {
			dfs(result / numbers[index], index + 1, op1, op2, op3, op4 - 1);
		}
	}
}
