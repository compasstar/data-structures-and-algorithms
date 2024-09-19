package algorithm.dp;

import java.util.*;
import java.io.*;


public class BOJ11054 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[] A;
    private static int[] dpLeft;
    private static int[] dpRight;

    public static void main(String[] args) throws Exception {
        setInput();

        for (int i = 0; i < N; i++) {
            getLengthOfLeft(i);
            getLengthOfRight(i);
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dpLeft[i] + dpRight[i]);
        }
        System.out.println(max - 1);
    }

    private static void setInput() throws Exception {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        dpLeft = new int[N];
        dpRight = new int[N];

        for (int i = 0; i < N; i++) {
            dpLeft[i] = 1;
            dpRight[i] = 1;
        }
    }

    private static int getLengthOfLeft(int index) {
        if (index == 0) {
            return 1;
        }

        if (dpLeft[index] > 1) {
            return dpLeft[index];
        }

        for (int i=0; i < index; i++) {
            if (A[index] > A[i]) {
                dpLeft[index] = Math.max(dpLeft[index], getLengthOfLeft(i) + 1);
            }
        }
        return dpLeft[index];
    }

    private static int getLengthOfRight(int index) {
        if (index == N-1) {
            return 1;
        }

        if (dpRight[index] > 1) {
            return dpRight[index];
        }

        for (int i=index + 1; i < N; i++) {
            if (A[index] > A[i]) {
                dpRight[index] = Math.max(dpRight[index], getLengthOfRight(i) + 1);
            }
        }
        return dpRight[index];

    }




}
