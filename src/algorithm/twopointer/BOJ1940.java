package algorithm.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1940 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N, M;
	private static int[] ingredients;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		setInput();
		int answer = getNumberOfArmor();
		System.out.println(answer);

	}
	
	private static void setInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		ingredients = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			ingredients[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static int getNumberOfArmor() {
		int cnt = 0;
		Arrays.sort(ingredients);
		
		int p1 = 0;
		int p2 = N-1;
		
		while (p1 < p2) {
			int sum = ingredients[p1] + ingredients[p2];
			if (sum < M) {
				p1++;
			} else if (sum > M) {
				p2--;
			} else if (sum == M) {
				
				int nextP1 = p1 + 1;
				while (nextP1 == p1) {
					nextP1++;
				}
				
				int nextP2 = p2 - 1;
				while (nextP2 == p2) {
					nextP2--;
				}
				cnt += (nextP1 - p1) * (p2 - nextP2);
				
				p1 = nextP1;
				p2 = nextP2;
			}
		}
		
		return cnt;
	}
}

