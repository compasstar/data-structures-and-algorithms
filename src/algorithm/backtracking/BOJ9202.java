package algorithm.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 * 트라이에 단어들을 집어 넣는다
 * bfs 로 보드를 돌면서 트라이가 contains 하는지 확인한다. 
 * 
 * 2. 시간복잡도
 * 트라이에 단어 삽입: 단어 수 * 단어 길이 = 300,000 * 8
 * 각 보드에서 bfs 돌면서 트라이 확인하기 -> 보드크기 * 단어길이 4 * 4 * 30
 * 위 과정을 보드 개수만큼 반복한다 -> * 30
 */


public class BOJ9202 {

	static class Point {
		int y;
		int x;
		Node node;

		public Point (int y, int x, Node node) {
			this.y = y;
			this.x = x;
			this.node = node;
		}
	}

	static class Node {
		Node[] childs = new Node[26];
		boolean last;
	}


	static class Trie {
		Node root;

		public Trie() {
			root = new Node();
		}

		public void add(String word) {
			Node nowNode = this.root;
			for (int i=0; i<word.length(); i++) {
				Node childNode = nowNode.childs[word.charAt(i) - 'A'];
				if (childNode == null) {
					childNode = new Node();
					nowNode.childs[word.charAt(i) - 'A'] = childNode;
				}
				nowNode = childNode;
			}
			nowNode.last = true;
		}

		public boolean contains(String word) {
			Node nowNode = this.root;
			for (int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				Node childNode = nowNode.childs[c - 'A'];
				if (childNode == null) {
					return false;
				}
				nowNode = childNode;
			}
			return true;
		}
	}

	private static final Integer BOARD_SIZE = 4;
	private static final Integer MAX_WORD_SIZE = 8;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int w;
	private static int b;
	
	private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	private static boolean[][] visited;
	private static int totalPoint;
	private static int cnt;
	private static String longest;
	private static Set<String> findWords = new HashSet<>();
	
	private static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
	private static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	
	private static Trie trie = new Trie();
	
	public static void main(String[] args) throws IOException {
		
		/**
		 * 사전정보 트라이에 삽입
		 */
		getWordDictionary();
		
		/**
		 * 보드 개수(b)만큼 반복하여 메인 로직을 실행한다
		 * findWords 셋에 단어들을 찾아서 삽입한다
		 */
		for (int k=0; k<b; k++) {
			//단어들 찾아서 set 에 삽입
			findWords(k);
			
			//정답 출력
			printAnswer();
		}
	}
	
	private static void getWordDictionary() throws IOException {
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		
		//트라이에 단어 삽입
		for (int i=0; i<w; i++) {
			trie.add(br.readLine());
		}
		br.readLine();
		
		st = new StringTokenizer(br.readLine());
		b = Integer.parseInt(st.nextToken());
	}
	
	private static void findWords(int k) throws IOException {
		//보드 만들기
		for (int i=0; i<BOARD_SIZE; i++) {
			board[i] = br.readLine().toCharArray();
		}
		if (k != b-1) {
			br.readLine();
		}
		
		findWords = new HashSet<>();
		for (int j=0; j<BOARD_SIZE; j++) {
			for (int i=0; i<BOARD_SIZE; i++) {
				Node nowNode = trie.root.childs[board[j][i] - 'A'];
				if (nowNode == null) {
					continue;
				}
				visited = new boolean[BOARD_SIZE][BOARD_SIZE];
				dfs(new Point(j, i, nowNode), board[j][i] + "");
			}
		}
	}
	
	private static void dfs(Point point, String nowWord) {
		if (nowWord.length() > MAX_WORD_SIZE) {
			return;
		}

		if (point.node.last) {
			findWords.add(nowWord);
		}
		
		for (int d=0; d<8; d++) {
			int nextY = point.y + dy[d];
			int nextX = point.x + dx[d];
			
			if (nextY < 0 || nextY >= BOARD_SIZE || nextX < 0 || nextX >= BOARD_SIZE || visited[nextY][nextX]) {
				continue;
			}
			
			String nextWord = nowWord + board[nextY][nextX];
			if (trie.contains(nextWord)) {
				visited[point.y][point.x] = true;
				dfs(new Point(nextY, nextX, point.node.childs[board[nextY][nextX] - 'A']), nextWord);
				visited[point.y][point.x] = false; 
			}
		}	
	}
	
	private static void printAnswer() {
		// 필요한 값들 초기화
		totalPoint = 0;
		longest = "";
		cnt = 0;
		
		List<String> tempFindWords = new ArrayList<>(findWords);
		Collections.sort(tempFindWords);
		for (String s : tempFindWords) {
			setAnswer(s);
		}
		System.out.println(totalPoint + " " + longest + " " + cnt);
	}
	
	private static void setAnswer(String word) {
		totalPoint += getScore(word.length());
		if (longest.length() < word.length()) {
			longest = word;
		} 
		cnt++;
	}
	
	private static int getScore(int length) {
		int point = 0;
		if (length >= 3 && length <= 4) {
			point += 1;
		} else if (length == 5) {
			point += 2;
		} else if (length == 6) {
			point += 3;
		} else if (length == 7) {
			point += 5;
		} else if (length == 8) {
			point += 11;
		}
		return point;
	}
}

