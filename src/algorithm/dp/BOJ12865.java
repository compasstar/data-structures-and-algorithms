package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * N: 물건 개수
 * W: 무게
 * V: 가치
 * K: 최대 K만큼의 무게
 * K이내의 무게합으로 최대한 큰 가치를 배낭에 넣으시오!
 * 그때의 가치합을 출력하라
 * 
 * 2. 알고리즘 및 시간복잡도
 * 무게에 비해 가치가 높은 물건들이 배낭에 들어가면 된다.
 * 완전탐색: 조합 100개 중에 ~~개 고르기. 최대: 100C50 --> 터질듯?
 * 
 * 그리디: 매번 최선의 결과가 결과적으로도 최선인가? --> 그렇지는 않다
 * 
 * DP: 결과적으로는 아무튼 최댓값인데, 결과는 잘 모름... --> 가능할까?
 * 물건들 중에 골라... 음
 * 
 * 
 * 3. 작업흐름
 * 
 *
 */


public class BOJ12865 {

	static class Thing {
		int w;
		int v;

		public Thing(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N, K;
	private static Thing[] things;
	private static int[][] dp; //index, weight

	public static void main(String[] args) throws IOException {
		setInput();
		int answer = getMaxValue(0, 0);
		System.out.println(answer);
	}
	
	private static void setInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		things = new Thing[N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			things[i] = new Thing(W, V);
		}
		
		dp = new int[N][100001];
	}
	
	private static int getMaxValue(int index, int sumOfWeight) {
		if (index == N) {
			return 0;
		}
		
		if (dp[index][sumOfWeight] > 0) {
			return dp[index][sumOfWeight];
		}
		
		
		if (sumOfWeight + things[index].w > K) {
			return getMaxValue(index + 1, sumOfWeight);
		}
		
		return dp[index][sumOfWeight] = Math.max(getMaxValue(index + 1, sumOfWeight + things[index].w) + things[index].v, getMaxValue(index + 1, sumOfWeight));
	}

}
