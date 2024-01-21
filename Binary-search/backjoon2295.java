import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 1. 아이디어
 * 집합을 일단 정렬한다
 * 3중 for 문으로 돌면 시간초과됨 N^3 = 10억 > 2억
 *
 * X + Y = K - Z
 * X + Y 리스트를 만든다
 * 2중 for 문 돌면서 K - Z 값이 해당 리스트에 존재하는지 이분탐색
 *
 * 2. 시간복잡도
 * N^2 logN
 *
 */


class Sort {

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



public class Main {

    private static int[] U;
    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        U = new int[N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            U[i] = Integer.parseInt(st.nextToken());
        }

        int[] twoU = new int[N*N];
        int twoIndex = 0;
        for (int j=0; j<N; j++) {
            for (int i=0; i<N; i++) {
                twoU[twoIndex] = U[j] + U[i];
                twoIndex++;
            }
        }

        Sort sort = new Sort();
        sort.mergeSort(twoU);
        sort.mergeSort(U);

        int maxValue = -1;
        for (int j=0; j<N; j++) {
            for (int i=0; i<N; i++) {
                if (binarySearch(twoU, U[j] - U[i])) {
                    maxValue = Math.max(maxValue, U[j]);
                }
            }
        }
        System.out.println(maxValue);
    }


    private static boolean binarySearch(int[] twoU, int number) {
        int start = -1;
        int end = N*N;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (twoU[mid] < number) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (twoU[end] == number) {
            return true;
        }
        return false;
    }

}
