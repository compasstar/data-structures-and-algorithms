import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[] score;

    public static void main(String[] args) throws Exception {
        setInput();
        double answer = getMean();
        System.out.println(answer);
    }

    private static void setInput() throws Exception{
        N = Integer.parseInt(br.readLine());
        score = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static double getMean() {
        double maxScore = 0;
        for (int i=0; i<N; i++) {
            maxScore = Math.max(maxScore, score[i]);
        }

        double totalScore = 0;
        for (int i=0; i<N; i++) {
            totalScore += (score[i] / maxScore) * 100;
        }
        return totalScore / N;
    }
}
