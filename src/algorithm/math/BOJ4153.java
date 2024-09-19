package algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ4153 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int A, B, C;

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (A == 0 && B == 0 && C == 0) {
                break;
            }
            if (isTri()) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }

    private static boolean isTri() {
        int[] l = new int[3];
        l[0] = A;
        l[1] = B;
        l[2] = C;
        Arrays.sort(l);
        if (Math.pow(l[0], 2) + Math.pow(l[1], 2) == Math.pow(l[2], 2)) {
            return true;
        }
        return false;
    }
}
