package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 재료 N개
 * 신맛 S, 쓴맛 B
 * 신맛: 곱, 쓴맛: 합
 * 신맛과 쓴맛의 차이를 작게 만들어야 한다.
 * 재료는 적어도 하나를 사용해야 한다.
 * 신맛과 쓴맛의 차이가 가장 작은 요리를 만드시오!
 * 
 * 모든 경우의 수를 따져본다. 
 * 중복순열 -> 백트래킹
 * 2진수로 나타낼 수 있다. 각 자리수가 재료를 나타낸다.
 * 
 * 2. 시간복잡도
 * 2^N = 2^10
 * 
 * 3. 자료구조
 * dfs로 재귀함수를 통해 다 한다.
 * 재료의 각 자리에 1을 넣었다 뺐다 하면서 해보자
 * algorithm.backtracking.Ingredient[] ingredients = new algorithm.backtracking.Ingredient[N];
 * 
 *
 */



public class BOJ2961 {

	static class Ingredient {
		int S;
		int B;

		public Ingredient(int S, int B) {
			this.S = S;
			this.B = B;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int N;
	private static Ingredient[] ingredients;
	private static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
    	N = Integer.parseInt(br.readLine());
    	ingredients = new Ingredient[N];
    	for (int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int S = Integer.parseInt(st.nextToken());
    		int B = Integer.parseInt(st.nextToken());
    		ingredients[i] = new Ingredient(S, B);
    	}
    	
//    	dfs(0, 1, 0);
    	dfsBit(0, 0B0000000000);
    	System.out.println(minDiff);
    }
    
    private static void dfs(int index, int totalS, int totalB) {
    	if (index == N) {
    		if (totalS == 1 && totalB == 0) {
    			return;
    		}
    		int totalDiff = Math.abs(totalS - totalB);
    		minDiff = Math.min(minDiff, totalDiff);
    		return;
    	}

    	//재료 추가함
    	dfs(index + 1, totalS * ingredients[index].S, totalB + ingredients[index].B);
    	
    	//재료 추가안함
    	dfs(index + 1, totalS, totalB);
    }
    
    private static void dfsBit(int index, int food) {
    	if (index == N) {
    		int totalS = 1;
    		int totalB = 0;
    		for (int i=0; i<N; i++) {
    			int temp = food % 2;
    			food /= 2;
    			if (temp == 1) {
    				totalS *= ingredients[i].S;
    				totalB += ingredients[i].B;
    			}
    		}
    		if (totalB == 0) {
    			return;
    		}
    		int diff = Math.abs(totalS - totalB);
    		minDiff = Math.min(minDiff, diff);
    		
    		return;
    	}

    	dfsBit(index + 1, food | 1 << index);
    	dfsBit(index + 1, food);
    }
}
