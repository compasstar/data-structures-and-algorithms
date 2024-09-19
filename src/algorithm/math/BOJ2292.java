package algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 1
 * 2 ~ 7 (6개)
 * 8 ~ 19 (12개)
 * 20 ~ 37 (18개)
 *
 * 1 + 6 + 6*2 + 6*3 + ...
 */

public class BOJ2292 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int number = 1;
        int index = 1;
        while (number < N) {
            number += index * 6;
            index++;
        }
        System.out.println(index);
    }


}
