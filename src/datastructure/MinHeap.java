package datastructure;//Priority Queue (Min Heap)
//백준 1927번 최소 힙

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinHeap {
	
	private static int[] heap;
	private static int size = 0;

	private static int N;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		heap = new int[N];
		
		StringBuilder answer = new StringBuilder();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
			if (cmd > 0) {
				push(cmd);
			} else if (cmd == 0) {
				answer.append(pop()).append('\n');
			}
		}
		
		System.out.println(answer);

	}
	
	private static void push(int value) {
		heap[size + 1] = value;
		size++;
		
		int current = size;
		while (current > 1 && heap[current] < heap[current / 2]) {
			int temp = heap[current];
			heap[current] = heap[current / 2];
			heap[current / 2] = temp;
			
			current /= 2;
		}
	}
	
	private static int pop() {
		if (size == 0) {
			return 0;
		}
		
		int value = heap[1];
		heap[1] = heap[size];
		size--;
		
		int current = 1;
		while (current * 2 <= size) {
			int child;
			if (current * 2 + 1 > size) {
				child = current * 2;
			} else {
				if (heap[current * 2] < heap[current * 2 + 1]) {
					child = current * 2;
				} else {
					child = current * 2 + 1;
				}
			}
			
			if (heap[current] < heap[child]) {
				break;
			}
			
			int temp = heap[current];
			heap[current] = heap[child];
			heap[child] = temp;
			
			current = child;
		}
		
		return value;
	}	
}
