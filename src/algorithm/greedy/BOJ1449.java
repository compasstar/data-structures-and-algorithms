package algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 1. 아이디어
 * 그냥 앞에서부터 차례로 테이프 길이만큼 붙인다
 *
 * 2. 시간복잡도
 * 한번 도는 거니까 O(N)
 * 근데 input numbers를 정렬해야하네요. O(NlogN)
 *
 * 3. 자료구조
 * 테이프가 붙여진 배열 tape[1000]
 * 물이 새는 곳 배열을 돌면서 테이프를 붙인다. tape[해당위치~테이프길이] 만큼을 1로 바꾼다
 *  주의! 조건을 tape 범위가 넘어가서 indexoutBound 에러가 뜨게하면 안됨. min으로 제한해주자!
 * 그리고 cnt++
 * 배열을 돌때 tape 가 이미 1이라면 continue
 *
 * cnt 출력
 */

public class BOJ1449 {

    static class Sort {

        private int[] sorted;

        public void mergeSort(int[] array) {
            sorted = new int[array.length];
            for (int size = 1; size < array.length; size *= 2) {
                for (int start = 0; start < array.length - size; start += size * 2) {
                    merge(array, start, start + size, Math.min(start + size * 2, array.length));
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

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Sort sort = new Sort();
        sort.mergeSort(numbers);

        int tape[] = new int[1001];
        int cnt = 0;

        for (int n : numbers) {
            if (tape[n] == 1) {
                continue;
            }
            for (int i=n; i<Math.min(n+L, 1001); i++) {
                tape[i] = 1;
            }
            cnt++;
        }

        System.out.println(cnt);
    }
}
