package algorithm.hashing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ20920 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    private static int N, M;
    private static Map<String, Integer> words;
    private static List<String> dictionary;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        words = new HashMap<>();

        for (int i=0; i<N; i++) {
            String word = br.readLine();
            if (word.length() >= M) {
                if (words.containsKey(word)) {
                    words.put(word, words.get(word) + 1);
                } else {
                    words.put(word, 1);
                }
            }
        }

        dictionary = new ArrayList<>(words.keySet());
        Collections.sort(dictionary);
        dictionary.sort((o1, o2) -> {
            if (words.get(o1) != words.get(o2)) {
                return words.get(o2) - words.get(o1);
            } else {
                return o2.length() - o1.length();
            }
        });

        for (String word : dictionary) {
            sb.append(word).append('\n');
        }

        System.out.println(sb);
    }

}
