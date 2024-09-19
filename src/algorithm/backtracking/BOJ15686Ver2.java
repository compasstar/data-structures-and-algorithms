package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 * 1. 아이디어
    1. 치킨집 M개를 골라서 도시 전체 치킨 거리 최솟값을 구하시오
    2. 전체 치킨집 개수 중에 M개를 고르시오 → 조합(Combination) → 백트래킹(DFS)
    3. 백트래킹을 통해 M개를 고르고 각 경우마다 최솟값을 구한다
    4. 최솟값을 구하는 방식은 M개의 치킨집 좌표와 집의 좌표를 모두 구한 뒤, r1-r2 + c1-c2 로 최솟값을 구한다.
2. 시간복잡도
    1. 백트래킹을 통해 M개를 고른다 → 13CM = 13! / (13-6!)6! = 13 * 12 * 11 * 10 * 9 * 8 / 6 * 5 * 4 * 3 * 2 * 1 = 1716
    2. 각 경우마다 최소거리를 구한다 → 집 개수 * 치킨집 개수 = N^2 = 2500
    3. O(10^3 * 10^3) = O(10^6)
    4. 아 근데… 백트래킹으로 M개 고르는 것과 각 경우마다 최소거리 구하는 것을 분리하면 시간이 곱하기가 아니라 더하기로 바뀌는데 가능하려나?? 될 거 같은데. 
    5. private static int[][] chooseChicken = new int[2000][N]; 이렇게 해서 미리 다 집어넣어두는거지. 그리고 이걸 돌면서 최소거리를 구하면 어떻게 될까? O(2000 * 2500) 음… 똑같네요? 별 의미 없네.
    6. 그래도 시간복잡도에서는 동일하지만, 구현을 분리하는데 있어서 코드가 더 깔끔해진다는 장점은 있는 것 같네요. 코드 리팩토링하는데 더 유용할 듯? 자바의 객체지향적인 장점도 살릴 수 있고요.
3. 작업흐름
    1. dfs(): 치킨집 고르면서 하나씩 늘린다. if 치킨집개수 M개라면 거리 구하기
    2. 거리 구하기: 고른 치킨집 인덱스를 기반으로 2중 for문으로 각 집에서 가장 가까운 치킨집 거리를 구한다. 그리고 최솟값을 구하여 덧씌운다
    3. private static algorithm.bfs.algorithm.backtracking.Point[] 치킨집좌표
    4. private static algorithm.bfs.algorithm.backtracking.Point[] 집좌표
    5. private static int[] 고른 치킨집 인덱스
    6. private static int minDistance 출력해야 할 정답이자 최소 치킨 거리
 */


public class BOJ15686Ver2 {

	static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	private static int[][] map;
	
	private static Point[] chicken;
	private static int chickenIndex = 0;
	private static final List<Point> house = new ArrayList<>();
	private static final Stack<Point> chickenSelected = new Stack<>();
	private static final List<Stack<Point>> chickenAll = new ArrayList<>();
	private static int minDistance = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException  {
		
		// 입력값을 받아서 변수를 입력한다
		getInput();
		
		//백트래킹을 통해 chickenAll에 M개의 치킨집을 담은 변수를 가득 저장한다
		selectMChicken(0, 0);
		
		//백트래킹으로 만든 chickenAll 을 돌면서 최소 치킨 거리를 구한다 
		getMinDistance();
		
		//정답 출력
		System.out.println(minDistance);
	}
	
	private static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		chicken = new Point[N*N];
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
				if (map[j][i] == 1) {
					house.add(new Point(j, i));
				} else if (map[j][i] == 2) {
					chicken[chickenIndex] = new Point(j, i);
					chickenIndex++;
				}
			}
		}
	}
	
	private static void selectMChicken(int count, int start) {
		if (count == M) {	
			chickenAll.add((Stack<Point>) chickenSelected.clone());	
			return;
		}
		
		for (int i=start; i<chickenIndex; i++) {
			chickenSelected.push(chicken[i]);
			selectMChicken(count + 1, i + 1);
			chickenSelected.pop();
		}
	}
	
	private static void getMinDistance() {
		for (Stack<Point> c : chickenAll) {
			minDistance = Math.min(minDistance, getDistance(c));
		}
	}
	
	private static int getDistance(Stack<Point> chickenList) {
		int totalDistance = 0;
		for (Point h : house) {
			int minDistance = Integer.MAX_VALUE;
			for (Point c : chickenList) {
				int nowDistance = Math.abs(c.y - h.y) + Math.abs(c.x - h.x);
				minDistance = Math.min(minDistance, nowDistance);
			}
			totalDistance += minDistance;
		}
		return totalDistance;
	}
}

