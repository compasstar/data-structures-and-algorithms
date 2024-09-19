package algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ12015 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int[] A;

	private static List<Integer> LIS;
	

	public static void main(String[] args) throws Exception {
		setInput();
		int answer = getLongestNumbers();
		System.out.println(answer);
	}
	
	private static void setInput() throws Exception {
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		LIS = new ArrayList<>();
	}
	
	private static int getLongestNumbers() {
		for (int a : A) {
			if (LIS.isEmpty() || LIS.get(LIS.size() - 1) < a) {
				LIS.add(a);
			} else {
				int index = binarySearch(a);
				LIS.set(index, a);
			}
		}
		return LIS.size();
	}
	
	private static int binarySearch(int number) {
		int start = -1;
		int end = LIS.size();
		
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if (LIS.get(mid) < number) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return end;
	}

}
