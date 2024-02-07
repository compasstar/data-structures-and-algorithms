/**
 * 1. 아이디어
 * - 단어 N개, K글자 가르친다, 단어는 anta ~ tica
 * - 읽을 수 있는 단어의 최댓값을 구하시오!
 * 
 * - 일단 {a, c, i, t, n} 5개는 반드시 가르쳐야 한다 
 * 21개 알파벳 중에 10개 고르고, 고른 알파벳으로 단어들 순회하며 읽을 수 있는지 확인한다
 * 
 * 
 * 2. 시간복잡도
 * N = 50, K = 26, 단어길이 = 15
 * 21개 알파벳 중에 10개 고르기 --> 21C10 = 352,716 
 * 고른 알파벳들로 단어 읽을 수 있는지 확인하기 --> 단어수 * 단어글자수 = 50 * 15 = 750
 * 352716 * 750 = 264,537,000 (2억 6천) 안될 것 같은데...?
 * 단어 글자 수를 줄여야 한다. 미리 단어의 앞 뒤 anta, tica를 없애놓자. 그러면 단어글자수가 15 - 8 = 7이 된다.
 * 단어수 * 단어글자수 = 50 * 7 = 350
 * 352716 * 350 = 1억 2천 --> 아슬아슬하게 가능하다.
 * 
 * 3. 작업흐름
 * int N, K
 * boolean[26] selected : 고른 알파벳들
 * int maxValue;
 * String[] words;
 * 
 * 
 * main
 * 미리 input을 받을 때에, 단어들에서 a, c, i, t, n 을 제외한다
 * K가 5보다 작으면 그냥 0개
 * K가 5이상이면 dfs 실행
 * 
 * private static void dfs(int depth, int start)
 * if depth == K - 5
 * maxValue = Math.max(maxValue, readable)
 * 
 * for (int i=start; i<26; i++)
 * selected[i] = true;
 * dfs(depth + 1, i + 1)
 * selected[i] = false;
 * 
 *  
 *  private static int readable() {
 *  단어들을 돌면서 읽을 수 있는지 cnt 해서 리턴한다
 *  
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int K;
	private static boolean[] selected;
	private static int maxValue;
	private static String[] words;
	
	public static void main(String[] args) throws IOException  {
		getInput();
		dfs(0, 0);
		System.out.println(maxValue);
	}
	
	private static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		selected = new boolean[26];
		selected['a'-'a'] = true;
		selected['c'-'a'] = true;
		selected['i'-'a'] = true;
		selected['t'-'a'] = true;
		selected['n'-'a'] = true;
		maxValue = 0;
		
		words = new String[N];
		for (int i=0; i<N; i++) {
			String tempWord = br.readLine();
			tempWord = tempWord.substring(4, tempWord.length() - 4);
			words[i] = tempWord;
		}
	}
	
	private static void dfs(int depth, int start) {
		if (depth == K - 5) {
			maxValue = Math.max(maxValue, readable());
			return;
		}
		
		for (int i=start; i<26; i++) {
			if (i == 'a'-'a' || i == 'c'-'a' || i == 'i'-'a' || i == 't'-'a' || i == 'n'-'a') {
				continue;
			}
			selected[i] = true;
			dfs(depth + 1, i + 1);
			selected[i] = false;
		}
	}
	
	private static int readable() {
		int cnt = 0;
		for (String word : words) {
			boolean sw = true;
			for (char c : word.toCharArray()) {
				if (selected[c - 'a'] == false) {
					sw = false;
				}
			}
			if (sw == true) {
				cnt++;
			}
		}
		return cnt;
	}
	

}
