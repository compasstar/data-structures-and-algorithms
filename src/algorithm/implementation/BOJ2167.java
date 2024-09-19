package algorithm.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * (i, j) ~ (x, y) 수들의 합을 구하라
 * 배열을 돌면서 i ~ x, j ~ y 로 2중 배열을 만들어서 돌면서 합을 구한다
 *
 * 2. 시간복잡도
 * N * M = 90000
 *
 * 3. 작업흐름
 * 배열을 만든다 사이즈 1씩 크게 만든다 (인덱스 1부터 시작)
 * 2중 for문으로 돌면서 합을 구함
 *
 */


public class BOJ2167 {

    private static int N;
    private static int M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];

        for (int j=1; j<N+1; j++) {
            st = new StringTokenizer(br.readLine());
            for (int i=1; i<M+1; i++) {
                map[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        for (int k=0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            printSum(i, j, x, y);
        }
    }

    private static void printSum(int i, int j, int x, int y) {
        int sum = 0;
        for (int column = i; column <= x; column++) {
            for (int row = j; row <= y; row++) {
                sum += map[column][row];
            }
        }
        System.out.println(sum);
    }
}
