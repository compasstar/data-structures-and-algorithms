import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 15686번 치킨 배달
 * 1. 아이디어
 * 치킨집 M개 빼고 전부 폐업시켜서 도시의 전체 치킨 거리가 가장 작아지도록 구하시오
 * 전체 치킨집 중에 M개를 골라서 치킨거리를 구한다
 * M개 고르는건 그냥 모든 경우의 수 해본다
 * 
 * 치킨 거리 구하기: 각 집에서 bfs로 돌면서 합을 구한다
 * 전체 치킨집 중에 M개 고르기 -> 조합: 백트래킹
 * 
 * 2. 시간복잡도
 * 
 * 
 * 3. 작업흐름
 * 
 */

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	private static int[][] map;
	
	private static int[][] chicken = new int[13][2];
	private static int chickenIndex = 0;
	private static boolean[] chickenVisited;
	
	private static int[][] house;
	private static int houseIndex = 0;
	
	private static int minDistance;

	
	public static void main(String[] args) throws IOException  {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		house = new int[N*N][2];
		minDistance = Integer.MAX_VALUE;
	
		
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				int num = Integer.parseInt(st.nextToken());
				map[j][i] = num;
				if (num == 2) {
					chicken[chickenIndex][0] = j;
					chicken[chickenIndex][1] = i;
					chickenIndex++;
				} else if (num == 1) {
					house[houseIndex][0] = j;
					house[houseIndex][1] = i;
					houseIndex++;
				}
			}
		}
		chickenVisited = new boolean[chickenIndex];
		dfs(0, 0);
		System.out.println(minDistance);
	}
	
	private static void dfs(int count, int start) {
		if (count == M) {
			int distance = 0;
			for (int j=0; j<houseIndex; j++) {
				int temp = Integer.MAX_VALUE;
				for (int i=0; i<chickenIndex; i++) {
					if (chickenVisited[i] == true) {
						temp = Math.min(temp, Math.abs(house[j][0] - chicken[i][0]) + Math.abs(house[j][1] - chicken[i][1]));
					}
				}
				distance += temp;
			}
			minDistance = Math.min(minDistance, distance);
			return;
		}
		
		for (int i=start; i<chickenIndex; i++) {
			chickenVisited[i] = true;
			dfs(count + 1, i + 1);
			chickenVisited[i] = false;
		}
	}

}

