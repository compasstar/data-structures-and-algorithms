import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 1. 아이디어
 * 병합정렬
 * 
 * 2. 시간복잡도
 * nlogn
 *
 * 3. 작업흐름
 * 병합정렬을 하다가 사이즈가 k가 되었을 때 출력하기
 *
 */



class Sort {

    int[] sorted;

    public void mergeSort(int[] array, int k) {
        sorted = new int[array.length];

        for (int size = 1; size < array.length; size *= 2) {
            if (array.length / size == k) {
                for (int s = 0; s < array.length; s++) {
                    System.out.print(sorted[s] + " ");
                }
                break;
            }
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

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        Sort sort = new Sort();
        sort.mergeSort(numbers, k);
    }

}
