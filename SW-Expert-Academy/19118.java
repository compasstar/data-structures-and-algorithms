import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 해변 -- 1 ~ N 집
 * 집은 오름차순으로 정렬되어야 한다 (아니면 허문다)
 * 최소로 집을 허물어서 오름차순으로 만들어라
 * 
 * 증가하는 부분수열을 구하라
 * 
 * 
 * 3. 작업흐름
 * int N
 * int[] houses : 집들의 높이를 의미한다
 * int maxRemain : 총 집들의 길이
 * int[] remain : 부분수열의 길이를 의미한다
 * 
 * 자신보다 더 작은 건물의 개수를 센다.
 * 
 * 
 */

public class Solution {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int[] houses;
	private static int[] remain;
	private static int maxRemain;



	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i<T+1; i++) {
			sb.append('#').append(i).append(' ').append(getBroke()).append('\n');
		}
		System.out.println(sb);
	}
	
	private static int getBroke() throws NumberFormatException, IOException {
		
		getInput();

		brokeHouses();
		
		return N - maxRemain;
	}
	
	private static void getInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		houses = new int[N];
		remain = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			houses[i] = Integer.parseInt(st.nextToken());
		}
		maxRemain = 0;
	}
	
	private static void brokeHouses() {
		for (int j=0; j<N; j++) {
			for (int i=0; i<j; i++) {
				if (houses[i] < houses[j]) {
					remain[j] = Math.max(remain[j], remain[i]);
				}
			}
			remain[j]++;
			maxRemain = Math.max(maxRemain, remain[j]);
		}
	}

}
