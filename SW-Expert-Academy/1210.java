import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 도착지점(2) 좌표를 구하고 거기부터 올라가자
 * 
 * 2. 시간복잡도
 * 사다리타고 올라간다를 -> (100 * 100)
 * 
 * 3. 작업흐름
 * 
 * 도착지점을 찾아 올라가기
 * 
 * 메서드 사다리타기(x좌표):
 * 현재좌표 = (100,x) 
 * while (y좌표가 N-1 도달할때까지)
 * 만약 왼쪽에 1이 있으면 while 문으로 끝까지 이동
 * 만약 오른쪽에 1이 있으면 while 문으로 끝까지 이동
 * y--
 * 
 * 
 */

public class Solution {
	
	private static final Integer MAX_SIZE = 100;
	private static final Integer TEST_CASE_NUMBER = 10;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int[][] map = new int[MAX_SIZE][MAX_SIZE];
	private static int endX;

	public static void main(String[] args) throws IOException {
		for (int t=1; t<TEST_CASE_NUMBER+1; t++) {
			makeMap();
			run(t);
		}
	}
	
	private static void makeMap() throws IOException {
		br.readLine();
		for (int j=0; j<MAX_SIZE; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<MAX_SIZE; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
				if (map[j][i] == 2) {
					endX = i;
				}
			}
		}
	}
	
	private static void run(int testCaseNumber) {
		int y = MAX_SIZE - 1;
		int x = endX;
		while (y > 0) {
			y--;
			if (x - 1 >= 0 && map[y][x-1] == 1) {
				while (x - 1 >= 0 && map[y][x-1] == 1) {
					x -= 1;
				}
			} else if (x + 1 < MAX_SIZE && map[y][x+1] == 1) {
				while (x + 1 < MAX_SIZE && map[y][x+1] == 1) {
					x += 1;
				}
			}
		}
		System.out.println("#" + testCaseNumber +  " " + x);
	}
}
