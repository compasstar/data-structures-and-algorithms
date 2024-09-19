package algorithm.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 2563번 색종이
/** 
 * 1. 아이디어
 * 검은색 색종이를 붙이고 검은색 영역의 넓이를 구하라
 * 2차원 배열로 흰색 도화지를 만든다 : 0으로 초기화
 * 주어진 값에 따라 흰색 도화지를 검은색으로 채워간다(검은색 색종이의 크기는 무조건 10x10이다) : 1로 변환
 * 2차원 배열을 돌면서 1인 값들의 총 합이 검은 영역의 넓이이다.
 * 
 * 2. 시간복잡도
 * 검은색 색종이 붙이기 100^2
 * N = 색종이의 수 = 100
 * 총 걸리는 시간 => 100^2 * N = 100^2 * 100 < 2억
 */


public class BOJ2563 {

	private static int[][] whitePaper = new int[100][100];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		for (int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int bottom = Integer.parseInt(st.nextToken());
			
			for (int j = bottom; j < bottom + 10; j++) {
				for (int i = left; i < left + 10; i++) {
					whitePaper[j][i] = 1;
				}
			}
		}
		
		int sum = 0;
		for (int j=0; j<100; j++) {
			for (int i=0; i<100; i++) {
				sum += whitePaper[j][i];
			}
		}
		System.out.println(sum);
	}
}

