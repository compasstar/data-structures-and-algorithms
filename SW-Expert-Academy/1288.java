import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 아이디어
 * 양 세기: N, 2N, 3N, ...
 * 번호의 각 자리수에서 0~9까지 모든 숫자를 보는 것은 최소 몇 번 양을 센 것일까?
 * 숫자 0~9 까지 visited로 설정하고 숫자가 나오면 true로 변경한다
 *
 */


public class Solution {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		int T = Integer.parseInt(br.readLine());
		for (int i=1; i<T+1; i++) {
			System.out.println("#" + i + " " + getSheep());
		}
	}
	
	private static int getSheep() throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[10];
		int number = N;
		
		int countN = 1;
		while (true) {	
			while (number > 0) {
				visited[number % 10] = true;
				number /= 10;
			}
			
			int cnt = 0;
			for (int i=0; i<10; i++) {
				if (visited[i] == true) {
					cnt++;
				}
			}
			
			if (cnt == 10) {
				return N * countN;
			}
			
			countN++;
			number = N * countN;
		}
	}
}
