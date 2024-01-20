import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 1. 아이디어
 * 뒤집기 최소 횟수를 구하여라
 * 첫 문자를 기준으로 다른 문자로 바뀔 때 ++
 *
 * 2. 시간복잡도
 * 그냥 한번 S를 돌면 되니까 O(N)
 * N = 1,000,000
 *
 * 3. 작업흐름
 * int cnt = 0
 * int 첫숫자 = 문자열[0]
 *
 * for 문으로 문자열을 돈다
 * if 첫숫자와 현재숫자가 다르면
 * cnt++
 *
 * cnt 출력
 */



public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();

        int cnt = 0;
        char firstNumber = S.charAt(0);
        char prevNumber = S.charAt(0);

        for (char s : S.toCharArray()) {
            if (prevNumber == firstNumber && s != firstNumber) {
                cnt++;
            }
            prevNumber = s;
        }
        System.out.println(cnt);
    }
}
