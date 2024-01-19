import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 왼쪽 오른쪽 모두 거리 2 이상의 공간이 확보될 때 조망권 확보
 * 조망권 확보된 세대의 수를 구하여라
 * 
 * 가로 < 1000
 * 양 쪽 맨 끝 두 칸은 건물 없음
 * 건물 개수 N = 1000
 * 건물 높이 = 255
 */


public class Solution {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int[] buildings;
	
	public static void main(String[] args) throws IOException {
		StringBuilder answer = new StringBuilder();
		for (int test_case = 1; test_case < 11; test_case++) {
			answer.append('#').append(test_case).append(' ').append(getNumber()).append('\n');
		}
		System.out.println(answer);
	}
	
	private static int getNumber() throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		buildings = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		
		int leftLeft;
		int left;
		int mid;
		int right;
		int rightRight;
		int cnt;
		int heightDistance;
		
		for (int i=2; i<N-2; i++) {
			leftLeft = i - 2;
			left = i - 1;
			mid = i;
			right = i + 1;
			rightRight = i + 2;
			cnt = 0;
			heightDistance = 255;
			
			if (buildings[mid] > buildings[leftLeft]) {
				heightDistance = Math.min(heightDistance, buildings[mid] - buildings[leftLeft]);
				cnt++;
			}
			if (buildings[mid] > buildings[left]) {
				heightDistance = Math.min(heightDistance, buildings[mid] - buildings[left]);
				cnt++;
			}
			if (buildings[mid] > buildings[right]) {
				heightDistance = Math.min(heightDistance, buildings[mid] - buildings[right]);
				cnt++;
			}
			if (buildings[mid] > buildings[rightRight]) {
				heightDistance = Math.min(heightDistance, buildings[mid] - buildings[rightRight]);
				cnt++;
			}
			
			if (cnt == 4) {
				sum += heightDistance;
			}
		}

		return sum;
	}
	
}

