import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 두께 D, 가로 W, 합격기준 K
 * 세로 방향에 대해서 동일한 특성의 셀들이 K개 이상 연속적으로 있어야 통과한다. 
 * 약품 투입하면 가로로 모든 셀 특성이 A 혹은 B로 통일된다.
 * 최소로 약품을 투입하여 성능검사를 통과하여라!!!!!!
 * 
 * 2. 알고리즘
 * D = 13, W = 20
 * 
 * <완전탐색>
 * 각 필름 온오프하면서 끝까지가면 K기준 통과했는지 체크하고, 약품투입 몇개했는지 확인하고 min 값 초기화한다.
 * 2^D * 맵의크기(D*W) = 2,129,920 가능!
 * 
 * 3. 작업흐름
 * 
 * static int D, W, K
 * static int[][] map
 * static int minDrug
 * 
 * 백트래킹으로 막마다 약품 투여
 * void dfs(int d, int cnt, int[][] map)
 * 	if (d == D && isPass()) minDrug = Math.min(minDrug, cnt) return
 * 
 * 	약품 투여 안함
 * 	dfs(d + 1, cnt, map)
 * 
 * 	약품 투여 함
 * 	for (int i=0; i<W; i++)
 * 		tempMap[d][i] = 1
 * 	dfs(d + 1, cnt + 1, tempMap)
 * 	for (int i=0; i<W; i++)
 * 		tempMap[d][i] = 0
 * 	dfs(d + 1, cnt + 1, tempMap)
 * 
 * 맵 돌면서 K기준 통과했는지 확인하기
 * boolean isPass()
 * 	boolean[] passCnt = new boolean[W];
 * 
 * 	for (int i=0; i<W; i++)
 * 		int nowCell = 0;
 * 
 * 		while (nowCell < D)
	 * 		int nextCell = nowCell + 1;
	 * 		
	 * 		while(map[nextCell][i] == map[nowCell][i])
	 * 			nextCell + 1
	 * 
	 * 		if (nextCell - nowCell >= K)
	 * 			passCnt[i] = true;
	 * 
	 * 		nowCell = nextCell;
	 * 
 * 	passCnt 돌면서 전부 true라면 return true, 아니면 return false		
 * 
 * 
 * 맵 깊은 복사
 * int[][] copyMap() 
 * 
 * 
 * 
 */



public class Solution {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int D; //높이
	private static int W; //가로
	private static int K; //통과기준
	
	private static int[][] map;
	private static int minDrug;
	

    public static void main(String args[]) throws NumberFormatException, IOException {
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t=1; t<T+1; t++) {
    		run();
    		sb.append('#').append(t).append(' ').append(minDrug).append('\n');
    	}
    	System.out.println(sb);

    }
    
    private static void run() throws IOException {
    	getInput();
    	dfs(0, 0, map);
    }
    
    private static void getInput() throws IOException {
    	st = new StringTokenizer(br.readLine());
    	D = Integer.parseInt(st.nextToken());
    	W = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	map = new int[D][W];
    	for (int j=0; j<D; j++) {
    		st = new StringTokenizer(br.readLine());
    		for (int i=0; i<W; i++) {
    			map[j][i] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	minDrug = Integer.MAX_VALUE;
    }
    
    private static void dfs(int d, int cnt, int[][] map) {
    	if (cnt >= minDrug) {
    		return;
    	}
    	
    	if (d == D) {
    		if (isPass(map)) {
    			minDrug = Math.min(minDrug, cnt);
    		}
    		return;
    	}
    	
    	//약품 투여 안함
    	dfs(d + 1, cnt, map);
    	
    	//약품 투여 함
    	int[][] tempMap = copyMap(map);
    	for (int i=0; i<W; i++) {
    		tempMap[d][i] = 0;
    	}
    	dfs(d + 1, cnt + 1, tempMap);
    	for (int i=0; i<W; i++) {
    		tempMap[d][i] = 1;
    	}
    	dfs(d + 1, cnt + 1, tempMap);
   
    }
    
    private static boolean isPass(int[][] map) {
    	for (int i=0; i<W; i++) {
    		boolean pass = false;
    		int nowCell = 0;
    		while (nowCell < D) {
    			int nextCell = nowCell + 1;
    			while (nextCell < D && map[nextCell][i] == map[nowCell][i]) {
    				nextCell++;
    			}
    			if (nextCell - nowCell >= K) {
    				pass = true;
    			}
    			nowCell = nextCell;
    		}
    		if (pass == false) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private static int[][] copyMap(int[][] map) {
    	int[][] newMap = new int[D][W];
    	for (int j=0; j<D; j++) {
    		for (int i=0; i<W; i++) {
    			newMap[j][i] = map[j][i];
    		}
    	}
    	return newMap;
    }

}
