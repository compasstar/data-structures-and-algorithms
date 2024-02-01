import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * N = 직사각형 수 = 100,000
 * H = 높이 = 1,000,000,000 (10억)
 * 히스토그램에서 가장 큰 직사각형의 넓이를 출력하시오!
 * 
 * N*H 판이라고 생각해보자. 높이 중 가장 높은 높이부터 하나씩 내려오면서 히스토그램이 있는 시작점과 엔드점을 잡는다
 * 이를 통해 넓이를 구한다. 그러면서 최고 높이를 계속 업데이트한다. 
 * 전부 다 돌려면 시간복잡도 N*H = 10^5 * 10억 .......... 초과됨.
 * 완전탐색으로는 불가능하다.
 * 
 * 각 막대의 최대 높이에서 왼쪽 오른쪽으로 뻗어나가며 넓이를 구한다. N * N = 10^10 시간초과됨. 
 * 
 * 스택: 높이 >= peek (push), 높이 < peek (pop) 하고 최대 넓이 업데이트
 * 넓이 = 바로 뒤 높이 * 바로 뒤 ~ 그다음스택peek 너비
 * 
 * 2. 시간복잡도
 * O(N)
 * 
 * 
 * 3. 작업흐름
 *
 */



public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int N;
	private static long[] histogram;
	private static long maxArea = 0;
	private static StringBuilder sb = new StringBuilder();
	
	
	public static void main (String[] args) throws IOException {
		
		while(true) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0) {
				break;
			}
			histogram = new long[N];
			maxArea = 0;
			for (int i=0; i<N; i++) {
				histogram[i] = Long.parseLong(st.nextToken());
			}
			
			Stack<Integer> stack = new Stack<>();
			
			for (int i=0; i<N; i++) {
				while (!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
					int heightIndex = stack.pop();
					long width;
					if (stack.isEmpty()) {
						width = i;
					} else {
						width = i - 1 - stack.peek();
						System.out.println("stack.peek() = " + stack.peek());
					}
					maxArea = Math.max(maxArea, (long)width * histogram[heightIndex]);
				}
				stack.push(i);
			}
			
			while (!stack.isEmpty()) {
				int heightIndex = stack.pop();
				long width;
				if (stack.isEmpty()) {
					width = N;
				} else {
					width = N - 1 - stack.peek();
				}
				maxArea = Math.max(maxArea, (long)width * histogram[heightIndex]);
			}
			sb.append(maxArea).append('\n');
		}
		System.out.println(sb);
	}

}
