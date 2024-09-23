package datastructure.stack;//백준 10828번 스택

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Stack {
	
	private static int[] stack;
	private static int size = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		stack = new int[N];
		
		StringBuilder answer = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			if (cmd.equals("push")) {
				push(Integer.parseInt(st.nextToken()));
			} else if (cmd.equals("pop")) {
				answer.append(pop()).append('\n');
			} else if (cmd.equals("size")) {
				answer.append(size()).append('\n');
			} else if (cmd.equals("empty")) {
				answer.append(empty()).append('\n');
			} else if (cmd.equals("top")) {
				answer.append(top()).append('\n');
			}
		}
		
		System.out.println(answer);
	}
	
	private static void push(int item) {
		stack[size] = item;
		size++;
	}
	
	private static int pop() {
		if (size == 0) {
			return -1;
		}
		int result = stack[size - 1];
		stack[size - 1] = 0;
		size--;
		return result;
	}
	
	private static int size() {
		return size;
	}
	
	private static int empty() {
		if (size == 0) {
			return 1;
		}
		return 0;
	}
	
	private static int top() {
		if (size == 0) {
			return -1;
		}
		return stack[size - 1];
	}
}
