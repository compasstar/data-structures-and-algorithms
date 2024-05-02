import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 잡을 수 있는 동물의 수를 출력하시오!
 * 
 * 2. 알고리즘 및 시간복잡도
 * N = 100,000 동물의 수
 * M = 100,000 사대의 수
 * L = 1,000,000,000 (10억) 사정거리
 * 지도의 크기 -> 1,000,000,000 (10억)
 * 
 * 각 동물마다 실행을 하는데 잡을 수 있는지 체크한다
 * 해당 값과 가장 가까운 사수의 위치를 구하고, 사정거리 내에 있는지 체크한다.
 * [이분탐색]
 * 시간복잡도: 동물의 수 * log(사대의 수)
 * 
 * 3. 작업흐름
 * 사대는 일단 정렬해야 한다. x축기준으로
 * 그리고 동물좌표마다 이분탐색으로 잡을 수 있는지 구하기
 * 
 * 
 *
 */

class Point implements Comparable<Point>{
	int y;
	int x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	public int compareTo(Point o) {
		return this.x - o.x;
	}
}

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N, M, L;
	private static int[] shoots;
	private static Point[] animals;


	public static void main(String[] args) throws Exception {
		setInput();
		Arrays.sort(shoots);
		int answer = getAnimalAmount();
		System.out.println(answer);
	}
	
	private static void setInput() throws Exception {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		shoots = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			shoots[i] = Integer.parseInt(st.nextToken());
		}
		
		animals = new Point[N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			animals[i] = new Point(y, x);
		}
	}
	
	private static int getAnimalAmount() {
		int catchCount = 0;
		for (Point animal : animals) {
			if (isCatched(animal.y, animal.x)) {
				catchCount++;
			}
		}
		return catchCount;
	}
	
	private static boolean isCatched(int y, int x) {
		int nearX = getNearX(x);
		if (y + Math.abs(nearX - x) > L) {
			return false;
		}
		return true;
	}
	
	private static int getNearX(int x) {
		int start = -1;
		int end = M;
		while (start + 1 < end) {
			int mid = (start + end) / 2;
			if (shoots[mid] < x) {
				start = mid;
			} else {
				end = mid;
			}
		}
		
		int near = 0;
		if (end == M) {
			near = shoots[end - 1];
		} else if (end < M) {
			near = shoots[end];
			if (end > 0) {
				if (x - shoots[end - 1] < shoots[end] - x) {
					near = shoots[end - 1];
				}
			}
		}
		
		return near;
	}
	


}
