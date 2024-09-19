package algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * A의 크기가 B보다 큰 쌍이 몇 개나 있는지 구하라
 * A와 B를 둘 다 정렬한다
 * A를 반복문으로 돌면서 B에서 a의 위치를 찾고 그것보다 작은 개수를 합한다
 *
 * 2. 시간복잡도
 * A를 반복문으로 돈다 N
 * B를 이분탐색한다 logM
 * NlogM
 *
 * 3. 작업흐름
 * cnt = 0
 * for (a : A) {
 *     cnt += 이분탐색 메서드
 * }
 *
 * 이분탐색 메서드 (a) {
 *     a위치 찾고 그 이하 개수 반환
 * }
 *
 */


public class BOJ7795 {

    static class Sort {

        int[] sorted;

        public void mergeSort(int[] array) {
            sorted = new int[array.length];

            for (int size = 1; size < array.length; size *= 2) {
                for (int start = 0; start < array.length - size; start += 2 * size) {
                    merge(array, start, start + size, Math.min(array.length, start + 2 * size));
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

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;
    private static int[] A;
    private static int[] B;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case < T+1; test_case++) {
            System.out.println(getPair());
        }
    }

    private static int getPair() throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = new int[M];
        for (int i=0; i<M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Sort sort = new Sort();
        sort.mergeSort(A);
        sort.mergeSort(B);


        int cnt = 0;
        for (int a : A) {
            cnt += binarySearch(a);
        }

        return cnt;
    }

    private static int binarySearch(int a) {
        int start = -1;
        int end = M;
        int mid;
        while (start + 1 < end) {
            mid = (start + end) / 2;
            if (B[mid] < a) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return end;
    }
}
