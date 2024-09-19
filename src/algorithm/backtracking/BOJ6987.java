package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 6개국, 한 번씩 모두 붙는다 (총 5경기)
 * 승, 무승부, 패 가능.
 * 가능한 결과인지 판별하기. 가능하면 1, 불가능하면 0 출력하시오!!
 * 
 * 2. 시간복잡도
 * 백트래킹
 * 한 나라의 승리 카운트 -1, 다른 나라의 패배 카운트 -1 이걸 모든 경우의 수 재귀로 돌면서 하기
 * 한 나라의 무승부 카운트 -1, 다른 나라의 무승부 카운트 -1
 * 
 * 모든 나라의 카운트가 무사히 0이 된다면 1을 리턴
 * 짝이 맞지 않는다면 0을 리턴
 * 
 * 3. 작업흐름
 * int[][] results : 경기결과
 * int answer: 처음에 0으로 설정, 하나라도 짝이 맞는다면 1로 바꾼다
 * 
 * void recur() : 백트래킹
 * if 만약 모두 적절히 카운트가 0이 된다면 answer = 1
 * 
 * 
 * result[] for문으로 돌면서 승리 패배 카운트 -1
 * recur()
 * 다시 카운트 한 거 + 1
 * 
 * 
 * 
 */

public class BOJ6987 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int[][] results;
	private static int answer;
	
	private static boolean[][] visited;
	
	

	public static void main(String[] args) throws IOException {
		
		for (int i=0; i<4; i++) {
			run();
		}

	}
	
	private static void run() throws IOException {
		
		results = new int[6][3];
		answer = 0;
		int draw = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int j=0; j<6; j++) {
			for (int i=0; i<3; i++) {
				results[j][i] = Integer.parseInt(st.nextToken());
				if (i == 1) {
					draw += results[j][i];
				}
			}
		}
		
		visited = new boolean[64][64];
		recurWinLose(30 - draw, 0);	
		
		visited = new boolean[64][64];
		recurDraw(draw, 0);
		
		if (answer == 2) {
			System.out.print(1 + " ");
		} else {
			System.out.print(0 + " ");
		}
	}
	
	private static void recurWinLose(int game, int team) {
		
		if (game == 0) {
			answer = 1;
			return;
		}
		
		if (team >= 6) {
			return;
		}
		
		//승리, 패배 짝으로 하나씩 제외하기
		if (results[team][0] == 1) {
			for (int i=0; i<6; i++) {
				if (team == i) {
					continue;
				}
				if (results[i][2] > 0 && !visited[1 << team][1 << i]) {
					results[team][0]--;
					results[i][2]--;
					
					visited[1 << team][1 << i] = true;
					visited[1 << i][1 << team] = true;
					
					recurWinLose(game - 2, team + 1);
		
					visited[1 << team][1 << i] = false;
					visited[1 << i][1 << team] = false;
					
					results[team][0]++;
					results[i][2]++;
				}
			}
		} else if (results[team][0] > 1) {
			for (int i=0; i<6; i++) {
				if (team == i) {
					continue;
				}
				if (results[i][2] > 0 && !visited[1 << team][1 << i]) {
					results[team][0]--;
					results[i][2]--;
					
					visited[1 << team][1 << i] = true;
					visited[1 << i][1 << team] = true;
					
					recurWinLose(game - 2, team);
					
					visited[1 << team][1 << i] = false;
					visited[1 << i][1 << team] = false;
					
					results[team][0]++;
					results[i][2]++;
				}
			}
		} else {
			recurWinLose(game, team + 1);
		}
	}
	
	private static void recurDraw(int game, int team) {
		if (game == 0) {
			if (answer == 1) {
				answer = 2;
			}
			return;
		}
		
		if (team >= 6) {
			return;
		}
		
		if (results[team][1] == 1) {
			for (int i=team + 1; i<6; i++) {
				if (results[i][1] > 0 && !visited[1 << team][1 << i]) {
					results[team][1]--;
					results[i][1]--;
					
					visited[1 << team][1 << i] = true;
					
					recurDraw(game - 2, team + 1);
					
					visited[1 << team][1 << i] = false;
					
					results[team][1]++;
					results[i][1]++;
				}
			}
		} else if (results[team][1] > 1) {
			for (int i=team + 1; i<6; i++) {
				if (results[i][1] > 0 && !visited[1 << team][1 << i]) {
					results[team][1]--;
					results[i][1]--;
					
					visited[1 << team][1 << i] = true;
					
					recurDraw(game - 2, team);
					
					visited[1 << team][1 << i] = false;
					
					
					results[team][1]++;
					results[i][1]++;
				}
			}
		} else {
			recurDraw(game, team + 1);
		}
		
	}
	
}
