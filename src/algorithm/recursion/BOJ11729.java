package algorithm.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11729 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int cnt = 0;
	private static StringBuilder sb = new StringBuilder();

	
	public static void main (String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		hanoi(N, 1, 2, 3);
		System.out.println(cnt);
		System.out.println(sb);
	}
	
	private static void hanoi(int number, int from, int mid, int to) {
		if (number == 1) {
			sb.append(from).append(' ').append(to).append('\n');
			cnt++;
			return;
		}
		
		hanoi(number-1, from, to, mid);
		sb.append(from).append(' ').append(to).append('\n');
		cnt++;
		hanoi(number-1, mid, from, to);
	}
}
