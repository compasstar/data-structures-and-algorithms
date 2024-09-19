package algorithm.twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 배열 A,B,C,D
 * 각 배열에서 하나씩 골랐을 때, 4개의 수의 합이 0이 되는 쌍의 수를 구하시오!
 * 
 * 2. 알고리즘
 * N = 4000
 * 
 * 완전탐색 --> 각 배열에서 하나씩 고른다 --> 시간복잡도: 4000^4 = 조가 넘어간다...
 * 값찾기 -> 정렬가능 -> 투포인터 or 이분탐색
 * 
 * A + B 배열을 만든다 O(N^2)
 * C + D 배열을 만든다 O(N^2)
 * AB, CD 두 배열을 정렬한다 O(N^2logN^2)
 * 투포인터로 양 끝을 잡고 합이 0이 되도록 한다 O(N^2)
 * 
 * 
 * 3. 작업흐름
 * 
 * static int cnt;
 * 
 * int[N^2] AB
 * int[N^2] CD
 * 
 * void sumArrays()
 * 
 * void countSumZero()
 * 	int p1 = 0
 * 	int p2 = N^2 - 1
 * 
 * while (p1 < N^2 && p2 >= 0)
 * if (AB[p1] + CD[p2] < 0) p1++
 * else if (AB[p1] + CD[p2] > 0) p2--
 * else if (AB[p1] + CD[p2] == 0) cnt++; p1++;
 * 
 */

public class BOJ7453 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int size;
	private static long[] A;
	private static long[] B;
	private static long[] C;
	private static long[] D;
	
	private static long[] AB;
	private static long[] CD;
	
	private static long cnt;


	public static void main(String[] args) throws NumberFormatException, IOException {
		//input 받기
		getInput();
		
		//배열 AB, CD로 합치고 정렬하기
		sumArrays();
		
		//투포인터로 0개수 구하기
		twoPointer();
		
		//정답 출력
		System.out.println(cnt);
	}
	
	private static void getInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		A = new long[N];
		B = new long[N];
		C = new long[N];
		D = new long[N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		cnt = 0;
		size = (int) Math.pow(N, 2);
	}
	
	private static void sumArrays() {
		AB = new long[size];
		CD = new long[size];
		
		int index = 0;
		for (int j=0; j<N; j++) {
			for (int i=0; i<N; i++) {
				AB[index] = A[j] + B[i];
				CD[index] = C[j] + D[i];
				index++;
			}
		}

		Arrays.sort(AB);
		Arrays.sort(CD);
	}

	
	private static void twoPointer() {
		int p1 = 0;
		int p2 = size - 1;
		
		while (p1 < size && p2 >= 0) {
			if (AB[p1] + CD[p2] < 0) {
				p1++;
			} else if (AB[p1] + CD[p2] > 0) {
				p2--;
			} else if (AB[p1] + CD[p2] == 0) {
				int nextP1 = p1 + 1;
				int nextP2 = p2 - 1;
				while ((nextP1 < size) && (AB[nextP1 - 1] == AB[nextP1])) {
					nextP1++;
				}
				while ((nextP2 >= 0) && (CD[nextP2 + 1] == CD[nextP2])) {
					nextP2--;
				}
				cnt += ((long)(nextP1 - p1) * (long)(p2 - nextP2));
				p1 = nextP1;
				p2 = nextP2;
			}
		}
	}
}
