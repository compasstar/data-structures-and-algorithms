package algorithm.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1 2 3 4 5 6 7
 * 3 제거
 * 
 * 1 2 4 5 6 7
 * 6제거
 * 
 * 1 2 4 5 7
 * 2제거
 * 
 * 1 4 5 7
 * 7제거
 * 
 * 1 4 5
 * 5제거
 * 
 * 1 4
 * 1제거
 * 
 * 4
 * 4제거
 * 
 */

public class BOJ1158 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Integer> numbers = new ArrayList<>();
		List<Integer> answer = new ArrayList<>();
		for (int i=1; i<N+1; i++) {
			numbers.add(i);
		}
		
		int index = K-1;
		
		
		for (int i=0; i<N; i++) {
			answer.add(numbers.remove(index));
			if (i == N-1) {
				break;
			}
			
			index = (index + K - 1) % numbers.size();	
		}
		
		System.out.print("<");
		for (int i=0; i<N; i++) {
			if (i == N-1) {
				System.out.print(answer.get(i) + ">");
				break;
			}
			System.out.print(answer.get(i) + ", ");
		}
	}
	

}
