package algorithm.sort;//병합정렬 (Merge algorithm.binarysearch.algorithm.implementation.datastructure.Sort)
//백준 2751번

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSort {

	static class Sort {

		private int[] sorted;

		public void mergeSort(int[] array) {
			sorted = new int[array.length];
			mergeSort(array, 0, array.length);
			sorted = null;
		}

		private void mergeSort(int[] array, int left, int right) {
			for (int size = 1; size < right; size *= 2) {
				for (int start = 0; start < right-size; start += (2*size)) {
					int low = start;
					int mid = start + size;
					int high = Math.min(start + 2*size, right);
					merge(array, low, mid, high);
				}
			}
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
				} else if (array[leftIndex] > array[rightIndex]) {
					sorted[sortedIndex] = array[rightIndex];
					rightIndex++;
					sortedIndex++;
				}
			}

			while (leftIndex < mid) {
				sorted[sortedIndex] = array[leftIndex];
				sortedIndex++;
				leftIndex++;
			}

			while (rightIndex < high) {
				sorted[sortedIndex] = array[rightIndex];
				sortedIndex++;
				rightIndex++;
			}

			for (int i = low; i < high; i++) {
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



