package algorithm.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 탑 N개
 * 탑 꼭대기에서 왼쪽으로 레이저 발사. 레이저 부딪히면 수신.
 * 각각의 탑에서 발사한 레이저를 어느 탑에서 수신하는지 출력하시오! (부딪히지 않으면 0 출력)
 * 스택: 가장 큰 탑을 스택의 peek으로 한다. 내림차순으로 만들기
 * 
 * 2. 시간복잡도
 * O(N)
 * 
 * 3. 작업흐름
 * 만약 더 큰 탑이 나오면, 반복해서 pop()  하면서 자기보다 작은 탑들 정리. 자기보다 큰 탑 나오면 해당 탑 인덱스 출력
 * 만약 더 작은 탑이 나오면, 그냥 push하고 peek() 출력하기
 */

public class BOJ2493 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int N;
	private static int[] top;
	
	public static void main (String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		top = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			top[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stack = new Stack<>();
		for (int i=0; i<N; i++) {
			while(!stack.isEmpty() && top[i] > top[stack.peek()]) {
				stack.pop();
			}
			if (!stack.isEmpty()) {
				System.out.print(stack.peek()+1 + " ");
			} else {
				System.out.print(0 + " ");
			}
			
			stack.push(i);
		}	
	}
	
}
