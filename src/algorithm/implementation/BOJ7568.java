package algorithm.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;




public class BOJ7568 {

	static class Big {
		int key;
		int weight;

		public Big (int key, int weight) {
			this.key = key;
			this.weight = weight;
		}
	}
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int N;
	private static Big[] bigs;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		bigs = new Big[N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int key = Integer.parseInt(st.nextToken());
			bigs[i] = new Big(key, weight);
		}
		

		for (int j=0; j<N; j++) {
			int cnt = 1;
			for (int i=0; i<N; i++) {
				if (bigs[j].weight < bigs[i].weight && bigs[j].key < bigs[i].key) {
					cnt++;
				}
			}
			System.out.print(cnt + " ");
		}
	}
}
