import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 모든 섬들을 연결해야 한다
 * 비용 = E * L^2
 * 비용이 최소화되면서 모든 섬들을 연결할 때, 최소 환경 부담금을 반올림하여 출력하시오!!!
 * 
 * 
 * 2. 알고리즘
 * 모든 노드 연결임으로 MST
 * 크루스칼 시간복잡도: edge 수 = N^2 = 10^6 가능!
 * 프림 시간복잡도: N^2
 * 
 * 3. 작업흐름
 * int[] parent
 * union, find
 * 
 * pq 에 전부 넣고, 빼면서 weight 를 더한다
 * 
 */

class Point {
	int y;
	int x;
	
	public Point() {
		
	}
	
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class Edge implements Comparable<Edge>{
	int from;
	int to;
	double weight;
	
	public Edge(int from, int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		
		double value = this.weight - o.weight;
		if (value > 0) {
			return 1;
		} else if (value < 0) {
			return -1;
		} else {
			return 0;
		}
	}
}


public class Solution {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int N;
	private static Point[] islands;
	private static double E;
	private static double minCost;

	
	private static int[] parent;
	private static Queue<Edge> pq;
	
	

	
    public static void main(String args[]) throws NumberFormatException, IOException {
    	int T = Integer.parseInt(br.readLine());
    	for (int t=1; t<T+1; t++) {
    		run();
    		sb.append('#').append(t).append(' ').append(Math.round(minCost)).append('\n');
    	}
    	System.out.println(sb);
    }
    
    private static void run() throws NumberFormatException, IOException {
    	getInput();
    	makeGraph();
    	getMinCost();
    }
    
    private static void getInput() throws NumberFormatException, IOException {
    	N = Integer.parseInt(br.readLine());
    	
    	islands = new Point[N+1];
    	for (int i=1; i<N+1; i++) {
    		islands[i] = new Point();
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for (int i=1; i<N+1; i++) {
    		islands[i].x = Integer.parseInt(st.nextToken());
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for (int i=1; i<N+1; i++) {
    		islands[i].y = Integer.parseInt(st.nextToken());
    	}
    	
    	E = Double.parseDouble(br.readLine());
    	
    	parent = new int[N+1];
    	for (int i=1; i<N+1; i++) {
    		parent[i] = i;
    	}
    	
    	minCost = 0;
    	pq = new PriorityQueue<>();
    }
    
    private static void getMinCost() {
    	while (!pq.isEmpty()) {
    		Edge nowEdge = pq.poll();
    		if (union(nowEdge.from, nowEdge.to)) {
    			minCost += nowEdge.weight;
    		}
    	}
    }
    
    private static void makeGraph() {
    	for (int j=1; j<N+1; j++) {
    		for (int i=j+1; i<N+1; i++) {
    			double distance = E * (Math.pow(islands[j].y - islands[i].y, 2) + Math.pow(islands[j].x - islands[i].x, 2));
    			pq.offer(new Edge(j, i, distance));
    		}
    	}
    }
    
    private static int find(int x) {
    	if (parent[x] == x) {
    		return x;
    	}
    	return parent[x] = find(parent[x]);
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
