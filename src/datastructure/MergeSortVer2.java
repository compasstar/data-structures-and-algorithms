package datastructure;//병합정렬 MergeSort
//백준 2751번 수 정렬하기 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSortVer2 {

	static class Sort {

		private int[] sorted;

		public void mergeSort(int[] array) {
			sorted = new int[array.length];
			for (int size = 1; size < array.length; size *= 2) {
				for (int start = 0; start < array.length - size; start += 2 * size) {
					merge(array, start, start + size, Math.min(start + 2 * size, array.length));
				}
			}
			sorted = null;
		}

		private void merge(int[] array, int low, int mid, int high) {
			int leftIndex = low;
			int rightIndex = mid;
			int sortedIndex = low;

			while (leftIndex < mid && rightIndex < high) {
				if (array[leftIndex] <= array[rightIndex]) {
					sorted[sortedIndex] = array[leftIndex];
					leftIndex++;
					sortedIndex++;
				} else {
					sorted[sortedIndex] = array[rightIndex];
					rightIndex++;
					sortedIndex++;
				}
			}

			while (leftIndex < mid) {
				sorted[sortedIndex] = array[leftIndex];
				leftIndex++;
				sortedIndex++;
			}
			while (rightIndex < high) {
				sorted[sortedIndex] = array[rightIndex];
				rightIndex++;
				sortedIndex++;
			}

			for (int i=low; i<high; i++) {
				array[i] = sorted[i];
			}
		}
	}

	private static int N;
	private static int[] array;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		array = new int[N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		Sort sort = new Sort();
		sort.mergeSort(array);
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			sb.append(array[i]).append('\n');
		}
		System.out.println(sb);
		
	}
}
