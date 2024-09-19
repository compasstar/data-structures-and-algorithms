package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 그래프에서 크기가 4이상인 트리가 있는지 확인하기
 * 
 * 2. 알고리즘 및 시간복잡도
 * 노드 하나를 정하고, 연결된 노드들로 쭉 이어가면서 빽트래킹 실행
 * 
 * 
 */

public class BOJ13023 {


	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N, M;
	private static List<Integer>[] graph;
	private static boolean[] isVisit;
	private static int answer;
	
	public static void main(String[] args) throws IOException {
		
		setInput();

		for (int i=0; i<N; i++) {
			isVisit[i] = true;
			isExistFriend(0, i);
			isVisit[i] = false;
			
			if (answer == 1) {
				break;
			}
		}
		
		System.out.println(answer);

	}
	
	private static void setInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N];
		for (int i=0; i<N; i++) {
			graph[i] = new ArrayList<>();
		}
		isVisit = new boolean[N];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		answer = 0;
	}
	
	private static void isExistFriend(int cnt, int index) {
		if (cnt == 4) {
			answer = 1;
			return;
		}
		
		for (int next : graph[index]) {
			if (isVisit[next]) {
				continue;
			}
			isVisit[next] = true;
			isExistFriend(cnt + 1, next);
			isVisit[next] = false;
		}
	}

}


