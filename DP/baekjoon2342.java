import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 0: 중심, 1: 위, 2: 왼쪽, 3: 아래, 4: 오른쪽
 * 두 발은 같은 위치에 있지 않는다.
 * 한 발씩 움직인다
 * 
 * 같은 지점 한번 더 -> 1의 힘
 * 중앙에서 다른 곳 -> 2의 힘
 * 인접한 위치 -> 3의 힘
 * 반대편 이동 -> 4의 힘
 * 
 * 게임을 진행했을 때 사용되는 최소의 힘을 구하시오!!
 * 
 * 2. 시간복잡도
 * - 명령 : 100,000 개
 * 
 * - 가장 먼저 백트래킹으로 모든 경우의 수를 탐색해보자
 * - 한 명령당 왼발로 할 지 오른발로 할 지 2개의 경우의 수 존재
 * - 2^100,000 > 2억 ====>>> 시간초과! 탐색은 불가능
 * 
 * - 그리디적인 사고로 구할 수 있을까? == 매번 최선의 선택을 했을 때 결과적으로 최선의 결과가 나올까?
 * - 매번 더 비용이 덜 드는 선택을 한다고 가정해보자.
 * - 2, 1, 4, 1 이라고 가정할 때 -> 비용은 왼발2 , 오른발2, 오른발3, 오른발3이 든다 -> 최선: 왼발2, 오른발2, 왼발4, 오른발1
 * - 그리디 불가능!!
 * 
 * - 탐색은 시간초과, 매번 최선의 선택 X --> 전부 탐색은 안되고, 원리는 모르겠지만 아무튼 최선의 결과를 내야한다!
 * - Top-Down 방식의 DP로 해결해보자
 * - MIN(여태까지의 최선의 전략 + 왼발, 여태까지의 최선의 전략 + 오른발) 로 재귀를 돌면서 사용한 힘을 계산한다
 * - DP 에서 visited 를 체크해주어야 한다. 현재 발 위치와 점수가 동일하다면 return 으로 끊어준다.
 * 
 * 3. 작업흐름
 * int N 명령의 개수
 * int[] cmd
 * int[][][] visited = new int[명령의 개수][5][5] : 현재 명령 인덱스, 왼발 위치, 오른발 위치 -> 사용한 힘이 들어있음
 * int minPower
 * 
 * void main
 * recur
 * 
 * 
 * int recur(int index, int 왼발, int 오른발)
 * if index == 명령의 개수 : return 0
 * 
 * if visited[index][왼발][오른발] 방문했다면 return visited[index][왼발][오른발]
 * 
 * 목적지 = cmd[index]
 * 
 * return visited[index][왼발][오른발] = MIN( recur(index + 1, 목적지, 오른발) + power(왼발, 목적지), recur(index + 1, 왼발, 목적지) + power(오른발, 목적지)) 
 * 
 * 
 * 힘 계산 메서드
 * int power(int from, int to)
 * 0에서 다른 곳 -> 2
 * 동일위치 -> 1
 * 인접위치 -> 3
 * 반대편 -> 4
 * 
 */

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N; //명령의 개수
	private static int[] cmd;
	private static int[][][] dp; //명령인덱스, 왼발위치, 오른발위치
	private static int minPower; //출력해야할 정답
	

	public static void main(String[] args) throws IOException {
		getInput();

		minPower = recur(0, 0, 0);
		System.out.println(minPower);
	}
	
	private static void getInput() throws IOException {
		
		N = 0;
		cmd = new int[100000];
		
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			int input = Integer.parseInt(st.nextToken());
			if (input == 0) {
				break;
			}
			
			cmd[N] = input;
			N++;
		}
		
		dp = new int[N][5][5];
		minPower = 0;	
	}
	
	private static int recur(int index, int left, int right) {
		if (index == N) {
			return 0;
		}
		
		//이미 방문했다면 바로 return 한다
		if (dp[index][left][right] != 0) {
			return dp[index][left][right];
		}
		
		int destination = cmd[index];
		
		//만약 destination 에 이미 발이 올라가있다면, 두 발을 한 위치에 올라갈 수 없으므로 경우의 수는 한 가지 이다.
		if (destination == left) {
			return dp[index][left][right] = recur(index + 1, destination, right) + power(left, destination);
		} else if (destination == right) {
			return dp[index][left][right] = recur(index + 1, left, destination) + power(right, destination);
		}	
		
		return dp[index][left][right] = Math.min(recur(index + 1, destination, right) + power(left, destination), recur(index + 1, left, destination) + power(right, destination));
	}
	
	private static int power(int from, int to) {
		//중앙에서
		if (from == 0) {
			return 2;
		}
		
		//같은 위치
		if (from == to) {
			return 1;
		}
		
		//반대편 위치
		if (Math.abs(to - from) == 2) {
			return 4;
		}
		
		//인접 위치
		return 3;
	}


	
}
