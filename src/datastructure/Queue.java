package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Queue {
	
	private static int[] queue;
	private static int back = 0;
	private static int front = 0;
	private static int size = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		queue = new int[N];
		
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
			} else if (cmd.equals("front")) {
				answer.append(front()).append('\n');
			} else if (cmd.equals("back")) {
				answer.append(back()).append('\n');
			}
		}
		
		System.out.println(answer);
	}
	
	private static void push(int item) {
		queue[back] = item;
		back++;
		size++;
	}
	
	private static int pop() {
		if (size == 0) {
			return -1;
		}
		int result = queue[front];
		queue[front] = 0;
		front++;
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
	
	private static int front() {
		if (size == 0) {
			return -1;
		}
		return queue[front];
	}
	
	private static int back() {
		if (size == 0) {
			return -1;
		}
		return queue[back - 1];
	}
}
