/**
 * 1. 아이디어
 * A: 매일 출근가능
 * B: 출근하면 다음날 쉬어야 한다
 * C: 출근하면 다음날, 다다음날 쉬어야 한다
 * 
 * 출근기록 S 의 모든 순열 중에 올바른 출근 기록 중 아무거나(?) 출력하시오!!
 * 만약 올바른 출근기록이 없다면 -1을 출력하라.
 * 
 * dfs 로 모든 출근기록을 돌면서 확인한다
 * 주의사항: visited로 똑같은 출근기록 또 확인할필요 없음
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static char[] S;
	private static boolean[][][][][] visited; // cntA, cntB, cntC, turnB, turnC
	private static int cntA;
	private static int cntB;
	private static int cntC;
	
	private static List<String> answers = new ArrayList<>();



	public static void main(String[] args) throws IOException {
		
		S = br.readLine().toCharArray();

		for (int i=0; i<S.length; i++) {
			if (S[i] == 'A') {
				cntA++;
			} else if (S[i] == 'B') {
				cntB++;
			} else if (S[i] == 'C') {
				cntC++;
			}
		}
		visited = new boolean[cntA+1][cntB+1][cntC+1][3][3];
		

		recur("", cntA, cntB, cntC, 0, 0);

		if (answers.size() == 0) {
			System.out.println(-1);
		} else {
			System.out.println(answers.get(0));
		}
		
	}
	
	private static void recur(String s, int cntA, int cntB, int cntC, int turnB, int turnC) {
		if (cntA == 0 && cntB == 0 && cntC == 0) {
			if (s.length() != 0) {
				answers.add(s);
			}
			return;
		}
		
		if (cntC > 0 && turnC == 0 && !visited[cntA][cntB][cntC-1][0][2]) {
			visited[cntA][cntB][cntC-1][0][2] = true;
			recur(s + 'C', cntA, cntB, cntC - 1, 0, 2);
		}
		if (cntB > 0 && turnB == 0 && !visited[cntA][cntB-1][cntC][1][Math.max(0,  turnC - 1)]) {
			visited[cntA][cntB-1][cntC][1][Math.max(0,  turnC - 1)] = true;
			recur(s + 'B', cntA, cntB-1, cntC, 1, Math.max(0, turnC - 1));
		}
		if (cntA > 0 && !visited[cntA-1][cntB][cntC][0][Math.max(0,  turnC - 1)]) {
			visited[cntA-1][cntB][cntC][0][Math.max(0,  turnC - 1)] = true;
			recur(s + 'A', cntA - 1, cntB, cntC, 0, Math.max(0, turnC - 1));
		}
	}

}
