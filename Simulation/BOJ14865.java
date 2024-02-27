/**
 * 1. 아이디어
 * 지도를 그리고 
 * 다른 봉우리에 의해 포함되지 않는 봉우리 개수 구하시오
 * 다른 봉우리를 포함하지 않는 봉우리 개수 구하시오
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class xAxis implements Comparable<xAxis> {

	boolean up;
	int x;

	public xAxis(boolean up, int x) {
		this.up = up;
		this.x = x;
	}

	@Override
	public int compareTo(xAxis o) {
		// TODO Auto-generated method stub
		return this.x - o.x;
	}
}


public class Main {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static List<xAxis> mountain;
	private static int cntNotContained;
	private static int cntNotContain;

	public static void main(String[] args) throws NumberFormatException, IOException {

		getInput();

		if (mountain.get(0).up == false) {
			xAxis temp = mountain.remove(0);
			mountain.add(temp);
		}
		
		for (int i=0; i<mountain.size() / 2; i++) {
			int start = mountain.get(2 * i).x;
			int end = mountain.get(2 * i + 1).x;
			if (start > end) {
				mountain.set(2 * i, new xAxis(false, start));
				mountain.set(2 * i + 1, new xAxis(true, end));
			}
		}
		
		Collections.sort(mountain);

		countNotContained();
		countNotContain();
		
		System.out.println(cntNotContained + " " + cntNotContain);

	}

	private static void getInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		mountain = new ArrayList<>();
		cntNotContained = 0;
		cntNotContain = 0;

		st = new StringTokenizer(br.readLine());
		int nowX = Integer.parseInt(st.nextToken());
		int nowY = Integer.parseInt(st.nextToken());

		int originX = nowX;
		int originY = nowY;


		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int nextX = Integer.parseInt(st.nextToken());
			int nextY = Integer.parseInt(st.nextToken());

			// 올라간다: 음수 -> 양수
			if (nextY > 0 && nowY < 0) {		
				mountain.add(new xAxis(true, nowX));
			}

			// 내려간다: 양수 -> 음수
			if (nextY < 0 && nowY > 0) {
				mountain.add(new xAxis(false, nowX));
			}
			nowY = nextY;
			nowX = nextX;
		}
		
		
		if (nowY < 0 && originY > 0) {
			mountain.add(new xAxis(true, originX));
		} else if (nowY > 0 && originY < 0) {
			mountain.add(new xAxis(false, originX));
		}

	}


	private static void countNotContained() {
		Stack<Integer> stackMountain = new Stack<>();
		for (xAxis axis : mountain) {
			boolean up = axis.up;
			if (up) {
				stackMountain.add(1);
			} else {
				stackMountain.pop();
				if (stackMountain.isEmpty()) {
					cntNotContained++;
				}
			}
		}
	}
	
	private static void countNotContain() {
		boolean nowStart = mountain.get(0).up;
		for (int i = 1; i < mountain.size(); i++) {
			boolean nextStart = mountain.get(i).up;

			if (nowStart == true && nextStart == false) {
				cntNotContain++;
			}

			nowStart = nextStart;
		}
	}
	
}
