/**
 * 
 * 1. 아이디어
 * DFS 백트래킹으로 모든 Core 의 상하좌우 전부 해보면서 MAX 값을 구한다
 * 
 * 2. 시간복잡도
 * (N^2 * 12) ^ 4 = 144 * 12 ^ 4 < 2억
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
	private static StringTokenizer st;
	private static int N;
	private static int[][] maxinose;
	
	private static int[][] cores;
	private static int coreSize;

	private static int minLength;
	private static int maxCore;

	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder answer = new StringBuilder();
		for (int test_case = 1; test_case < T + 1; test_case++) {
			answer.append('#').append(test_case).append(' '). append(getLength()).append('\n');
		}
		System.out.println(answer);
	}
	
	private static int getLength() throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		maxinose = new int[N][N];
		minLength = 144;
		maxCore = 0;
		
		cores = new int[12][2];
		coreSize = 0;
		
		for (int j = 0; j < N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				maxinose[j][i] = Integer.parseInt(st.nextToken());
				if (maxinose[j][i] == 1) {
					cores[coreSize][0] = j;
					cores[coreSize][1] = i;
					coreSize++;
				}
			}
		}
		
		dfs(0, 0, 0);

		return minLength;
	}
	
	private static void dfs(int coreIndex, int length, int coreNumber) {
		
		if (coreIndex == coreSize) {
			if (coreNumber > maxCore) {
				minLength = length;
				maxCore = coreNumber;	
			} else if (coreNumber == maxCore) {
				minLength = Math.min(minLength, length);
			}
			return;
		}
		
		if (coreSize - coreIndex + coreNumber < maxCore) {
			return;
		}
		
		
		/**
		 * 방향: Y - 1
		 */
		int nowY = cores[coreIndex][0] - 1;
		int nowX = cores[coreIndex][1];
		int check = 0;
		int nowLength = 0;
		
		while (nowY >= 0 && maxinose[nowY][nowX] == 0) {
			maxinose[nowY][nowX] = 2;
			nowY--;
		}
		if (nowY < 0) {
			check++;
			nowLength = cores[coreIndex][0];
		}
		dfs(coreIndex + 1, length + nowLength, coreNumber + check);
		
		//원상복귀
		while (nowY < cores[coreIndex][0] - 1) {
			nowY++;
			maxinose[nowY][nowX] = 0;
		}
		
		/**
		 * 방향: Y + 1
		 */
		nowY = cores[coreIndex][0] + 1;
		nowX = cores[coreIndex][1];
		check = 0;
		nowLength = 0;
		
		while (nowY < N && maxinose[nowY][nowX] == 0) {
			maxinose[nowY][nowX] = 2;
			nowY++;
		}
		if (nowY >= N) {
			check++;
			nowLength = N - cores[coreIndex][0] - 1;
		}
		dfs(coreIndex + 1, length + nowLength, coreNumber + check);
		
		while (nowY > cores[coreIndex][0] + 1) {
			nowY--;
			maxinose[nowY][nowX] = 0;
		}
		
		
		/**
		 * 방향: X - 1
		 */
		nowY = cores[coreIndex][0];
		nowX = cores[coreIndex][1] - 1;
		check = 0;
		nowLength = 0;
		
		while (nowX >= 0 && maxinose[nowY][nowX] == 0) {
			maxinose[nowY][nowX] = 2;
			nowX--;
		}
		if (nowX < 0) {
			check++;
			nowLength = cores[coreIndex][1];
		}
		dfs(coreIndex + 1, length + nowLength, coreNumber + check);
		
		while (nowX < cores[coreIndex][1] - 1) {
			nowX++;
			maxinose[nowY][nowX] = 0;
		}
		
		/**
		 * 방향 X + 1
		 */
		nowY = cores[coreIndex][0];
		nowX = cores[coreIndex][1] + 1;
		check = 0;
		nowLength = 0;
		
		while (nowX < N && maxinose[nowY][nowX] == 0) {
			maxinose[nowY][nowX] = 2;
			nowX++;
		}
		if (nowX >= N) {
			check++;
			nowLength = N - cores[coreIndex][1] - 1;
		}
		dfs(coreIndex + 1, length + nowLength, coreNumber + check);
		
		while (nowX > cores[coreIndex][1] + 1) {
			nowX--;
			maxinose[nowY][nowX] = 0;
		}
	}
}

