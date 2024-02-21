import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * N개의 도시(Node), M개의 도로(Edge)
 * 비용이 더 적은 쪽의 Edge를 연결해야 한다
 * 한 도로의 집합에서 도시를 이동할 수 있으면서, 비용이 가장 적은 집할을 찾으시오!!!
 * 만약 정답이 없으면 -1 을 출력한다 
 * 
 * 2. 알고리즘
 * N = 50, M = 1000
 * M개 도로를 고르고(모든 도시는 연결된다), 그 중에서 비용이 가장 적게 드는 것을 고르시오!
 * --> MST
 * 
 * 3. 작업흐름
 * int N, M
 * boolean[][] graph = new boolean[N][M];
 * 
 */

class Edge implements Comparable<Edge>{
	
	//from 이 to 보다 무조건 더 작다
	int from;
	int to;
	
	public Edge(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.from > o.from) {
			return 1;
		} else if (this.from < o.from) {
			return -1;
		} else {
			if (this.to > o.to) {
				return 1;
			} else if (this.to < o.to) {
				return -1;
			} else {
				return 0;
			}
		}
	}

}

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static int M;
	private static List<Edge> edges;
	
	private static int[] parent;

	public static void main(String[] args) throws IOException  {
		
		getInput();
		getMST();
		System.out.println(sb);

	}
	
	private static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>();
		for (int j=1; j<N+1; j++) {
			char[] line = br.readLine().toCharArray();
			for (int i=j+1; i<N + 1; i++) {
				if (line[i - 1] == 'Y') {
					edges.add(new Edge(j, i));
				}
			}
		}
		
		parent = new int[N+1];
		for (int i=1; i<N+1; i++) {
			parent[i] = i;
		}
	}
	
	private static void getMST() {
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (Edge edge : edges) {
			pq.offer(edge);
		}
		
		if (edges.size() < M) {
			sb.append(-1);
			return;
		}
		
		int[] count = new int[N+1];
		int countM = 0;
		
		Queue<Edge> edgeRemain = new LinkedList<>();
		
		while (!pq.isEmpty() && countM < M) {
			Edge nowEdge = pq.poll();
	
			if (!union(nowEdge.from, nowEdge.to)) {
				edgeRemain.add(nowEdge);
				continue;
			}	
			
			count[nowEdge.from]++;
			count[nowEdge.to]++;	
			countM++;
		}
		
		//모든 노드가 연결이 되지 않을 경우 -1 리턴
		if (countM != N - 1) {
			sb.append(-1);
			return;
		}
		
		
		while (!edgeRemain.isEmpty() && countM < M) {
			Edge nowEdge = edgeRemain.poll();
			count[nowEdge.from]++;
			count[nowEdge.to]++;	
			countM++;
		}


		
		for (int i=1; i < N+1; i++) {
			sb.append(count[i]).append(' ');
		}
	}
	
	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return find(parent[x]);
	}
	
	private static boolean union(int y, int x) {
		y = find(y);
		x = find(x);
		
		if (y == x) {
			return false;
		}
		
		if (y < x) {
			parent[x] = y;
		} else if (y > x) {
			parent[y] = x;
		}
		
		return true;
	}
	
}
