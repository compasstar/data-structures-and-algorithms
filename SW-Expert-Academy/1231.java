import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


class Node {
	char c;
	Node left;
	Node right;
	public Node() {
		
	}
	
	public Node(char c) {
		this.c = c;
	}
}


public class Solution {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb;
	
	private static int N; 
	private static Node[] nodes;


	public static void main(String[] args) throws NumberFormatException, IOException {
		

		for (int i=1; i<11; i++) {
			sb = new StringBuilder();
			sb.append('#').append(i).append(' ');
			run();
			sb.append('\n');
			System.out.println(sb);
		}


	}
	
	private static void run() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		nodes = new Node[N+1];
		
		for (int i=1; i<N+1; i++) {
			nodes[i] = new Node();
		}
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			nodes[number].c = c;

			
			if (number * 2 <= N) {
				nodes[number].left = nodes[Integer.parseInt(st.nextToken())];
			}
			if (number * 2 + 1 <= N) {
				nodes[number].right = nodes[Integer.parseInt(st.nextToken())];
			}
		
		}
		
		inOrder(nodes[1]);
	}
	
	private static void inOrder(Node node) {
		if (node == null) {
			return;
		}
		inOrder(node.left);
		sb.append(node.c);
		inOrder(node.right);
	}
}
