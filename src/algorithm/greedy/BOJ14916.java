package algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 거스름돈 동전의 최소 개수를 출력한다. 못 거슬러 주면 -1
 * 거스름돈은 2원과 5원만 존재 (개수는 무한)
 * 
 * 
 * 거스름돈이 5의 배수라면 5로 나눈 몫으로 즉시 반환
 * 5의 배수가 아니라면 2원씩 빼가면서 5의 배수가 될 때까지 뺀다
 * 만약 그러다 거스름돈이 0보다 작아진다면 -1 출력
 * 
 * 2. 시간복잡도
 * 거스름돈 n = 100,000
 * O(n) < 2억
 * 
 * 3. 작업흐름
 * while (거스름돈 % 5 != 0) 
 * {
 * 거스름돈 -= 2
 * 2원 count++
 * }
 * 
 * 만약 거스름돈이 0보다 작다면 -1 출력
 * 2원 count + 남은 거스름돈 / 5 출력
 */

public class BOJ14916 {
	
	public static void main(String[] args) throws IOException  {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int countCoin = 0;
		
		while (n % 5 != 0) {
			n -= 2;
			countCoin++;
		}
		
		if (n < 0) {
			System.out.println(-1);
			return;
		}
		
		countCoin += (n / 5);
		System.out.println(countCoin);
	}
}

