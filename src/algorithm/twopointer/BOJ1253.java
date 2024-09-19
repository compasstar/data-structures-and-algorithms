package algorithm.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1253 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int[] numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		
		Arrays.sort(numbers);
		
		int cnt = 0;
		for (int i=0; i<N; i++) {
			if (isGoodNumber(i)) {
				cnt++;
			}
		}
		System.out.println(cnt);
		
	}
	
	private static boolean isGoodNumber(int index) {

		int p1 = 0;
		int p2 = N - 1;
		
		while (p1 < p2) {
			if (p1 == index) {
				p1++;
				continue;
			} else if (p2 == index) {
				p2--;
				continue;
			}
			
			int tempSum = numbers[p1] + numbers[p2];
			
			if (tempSum == numbers[index]) {
				return true;
			} else if (tempSum < numbers[index]) {
				p1++;
			} else if (tempSum > numbers[index]) {
				p2--;
			}
		}
		
		return false;
	}

}
