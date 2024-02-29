/**
 * 1. 아이디어
 * 빈칸 : 0 이 주어진다
 * 스도쿠 규칙에 맞추어 모든 빈칸을 채우시오!
 * 스도쿠 판을 채울 수 없는 경우는 없다!
 * 
 * 2. 알고리즘
 * 모든 빈칸에 1~9 숫자를 한번씩 넣어보며 규칙에 맞도록 전부 넣는다
 * 하나라도 성공하는 경우 그 즉시 멈추고 답을 출력한다.
 * 
 * 3. 작업흐름
 * 
 * class Point
 * 
 * 
 * static int[][] map
 * List<Point> emptyCells : 0인 칸의 좌표를 채운다
 * 
 * void 백트래킹()
 * if (emptyCells.size() == 0)
 * 	return 
 * 
 * 
 * Point emptyCell = emptyCells.pop()
 * 
 * boolean[10] enableNumbers = Arrays.fill(true);
 * 가로, 세로 검사
 * 3x3 검사
 * 
 * for ()
 * 	map[emptyCell.y][emptyCell.x] = enableNumber
 * 	백트래킹()
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Point {
	int y;
	int x;
	
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	
	private static final Integer SDOKU_SIZE = 9;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int[][] map;
	private static Stack<Point> emptyCells;
	private static boolean gameEnd;
	


	public static void main(String[] args) throws IOException  {
		
		setInput();
		enterNumber();

	}
	
	private static void setInput() throws IOException {
		map = new int[SDOKU_SIZE][SDOKU_SIZE];
		emptyCells = new Stack<>();
		for (int j=0; j<SDOKU_SIZE; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<SDOKU_SIZE; i++) {
				map[j][i] = Integer.parseInt(st.nextToken());
				if (map[j][i] == 0) {
					emptyCells.push(new Point(j, i));
				}
			}
		}
	}
	
	private static void enterNumber() {
		if (gameEnd) {
			return;
		}
		
		if (emptyCells.isEmpty()) {
			gameEnd = true;
			printMap();
			return;
		}
		
		boolean[] enableNumbers = new boolean[SDOKU_SIZE + 1];
		Arrays.fill(enableNumbers, true);
		
		Point cell = emptyCells.pop();
		
		//가로 검사
		for (int i=0; i<SDOKU_SIZE; i++) {
			int tempCellNumber = map[cell.y][i];
			enableNumbers[tempCellNumber] = false;
		}
		
		//세로 검사
		for (int j=0; j<SDOKU_SIZE; j++) {
			int tempCellNumber = map[j][cell.x];
			enableNumbers[tempCellNumber] = false;
		}
		
		//3x3 검사
		int yRange = (cell.y / 3) * 3;
		int xRange = (cell.x / 3) * 3;
		for (int j=yRange; j<yRange+3; j++) {
			for (int i=xRange; i<xRange+3; i++) {
				int tempCellNumber = map[j][i];
				enableNumbers[tempCellNumber] = false;
			}
		}
		emptyCells.push(cell);
		for (int i=1; i<enableNumbers.length; i++) {
			if (enableNumbers[i] == true) {
				map[cell.y][cell.x] = i;
				cell = emptyCells.pop();
				enterNumber();
				map[cell.y][cell.x] = 0; 
				emptyCells.push(cell);
			}
		}
	}
	
	private static void printMap() {
		for (int j=0; j<SDOKU_SIZE; j++) {
			for (int i=0; i<SDOKU_SIZE; i++) {
				sb.append(map[j][i]);
				
				if (i != SDOKU_SIZE - 1) {
					sb.append(' ');
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	

}
