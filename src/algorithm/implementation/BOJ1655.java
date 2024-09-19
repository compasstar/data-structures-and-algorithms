package algorithm.implementation; /**
 * 1. 아이디어
 * maxHeap < minHeap 상태로 하여 maxHeap의 최댓값을 출력한다
 * 
 * 2. 시간복잡도
 * 힙에 삽입: logN
 * N번 반복하므로 NlogN
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1655 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int N;

	public static void main(String[] args) throws IOException {
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int i=1; i<N+1; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if (i % 2 == 1) {
				maxHeap.add(num);
				
			} else {
				minHeap.add(num);
			}
			
			if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
				minHeap.add(maxHeap.poll());
				maxHeap.add(minHeap.poll());
			}
			
			sb.append(maxHeap.peek()).append('\n');
		}
		
		System.out.println(sb);
	}
}
