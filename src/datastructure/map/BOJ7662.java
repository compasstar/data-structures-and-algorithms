package datastructure.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * 1. 아이디어
 * 삽입
 * 삭제 -> 우선순위 가장 높은 것, 가장 낮은 것
 * Q에 저장된 데이터 중 삽입 삭제를 처리한 후, 최댓값 최솟값을 출력하시오
 * Q가 비어있다면 EMPTY 출력

 * I n -> 삽입
 * D 1 -> 최댓값 삭제
 * D -1 -> 최솟값 삭제

 * 2. 알고리즘 및 시간복잡도
 * 트리맵을 통해 정렬하고 삽입 삭제를 해보자
 * 모든 경우 logN
 *
 * 3. 작업흐름
 * 트리맵의 key 에는 해당 숫자 저장
 * 트리맵의 value 에는 해당 숫자의 개수 저장
 * 
 */

public class BOJ7662 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static final StringBuilder sb = new StringBuilder();

    private static final TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            run();
        }
        System.out.println(sb);
    }

    private static void run() throws IOException {
        treeMap.clear();
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            String commend = st.nextToken();
            int number = Integer.parseInt(st.nextToken());

            if (commend.equals("I")) {
                insert(number);
            } else if (commend.equals("D")) {
                remove(number);
            }
        }

        if (treeMap.isEmpty()) {
            sb.append("EMPTY\n");
            return;
        }

        sb.append(treeMap.lastKey());
        sb.append(" ");
        sb.append(treeMap.firstKey());
        sb.append("\n");
    }

    private static void insert(int number) {
        if (treeMap.get(number) == null) {
            treeMap.put(number, 1);
            return;
        }
        treeMap.put(number, treeMap.get(number) + 1);
    }

    private static void remove(int number) {
        if (treeMap.isEmpty()) {
            return;
        }

        if (number == -1) {
            if (treeMap.get(treeMap.firstKey()) == 1) {
                treeMap.remove(treeMap.firstKey());
                return;
            }
            treeMap.put(treeMap.firstKey(), treeMap.get(treeMap.firstKey()) - 1);
        } else if (number == 1) {
            if (treeMap.get(treeMap.lastKey()) == 1) {
                treeMap.remove(treeMap.lastKey());
                return;
            }
            treeMap.put(treeMap.lastKey(), treeMap.get(treeMap.lastKey()) - 1);
        }
    }
}
