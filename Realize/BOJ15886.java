import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static char[] map;
    private static boolean[] isVisit;

    private static boolean[] tempVisit;

    public static void main(String[] args) throws IOException {
        setInput();
        int answer = getNumberOfPut();
        System.out.println(answer);
    }

    private static void setInput() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N];
        map = br.readLine().toCharArray();
        isVisit = new boolean[N];
    }

    private static int getNumberOfPut() {
        int cnt = 0;
        for (int i=0; i<N; i++) {
            tempVisit = new boolean[N];
            if(move(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean move(int index) {
        if (index < 0 || index >= N) {
            return true;
        }
        if (tempVisit[index]) {
            return true;
        }
        if (isVisit[index]) {
            return false;
        }

        isVisit[index] = true;
        tempVisit[index] = true;
        if (map[index] == 'E') {
            return move(index + 1);
        } else if (map[index] == 'W') {
            return move(index - 1);
        }

        return false;
    }


}
