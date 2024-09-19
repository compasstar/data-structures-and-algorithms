package algorithm.hashing;//백준 15829번 Hashing

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15829 {
	
	private static int L;
	private static String input;
	private static int M = 1234567891;

	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		input = br.readLine();
		
		System.out.println(calculateHash(input));
	}
	
	/**
	 * 모듈러 연산
	 * (a + b) mod n = (a mod n + b mod n) mod n
	 * (a - b) mod n = (a mod n - b mod n) mod n
	 * (a * b) mod n = (a mod n * b mod n) mod n
	 */
	private static long calculateHash(String key) {
		
		long hash = 0;
		double m = Math.pow(31, 0);
		for (int i=0; i<key.length(); i++) {
			int alpha = key.charAt(i) - 'a' + 1;
			hash += (alpha * m) % M;
			hash %= M;
			m = (m * 31) % M;
		}
		return hash;
	}
}



