package algorithm.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 발전소로부터 모든 도시 케이블 연결 비용 최소화 --> 미니멈 스패닝 트리
 * N개의 도시 M개의 케이블, K개의 발전소
 * 하나의 도시는 하나의 발전소에서만 전기를 공급받아야 한다
 * 
 * 전부 힙에 넣은 다음에 weight 가장 작은 edge 를 꺼내서 연결하는데 
 * find 가 연결되어있으면 같은 스패닝트리이기 때문에 연결하지 않는다
 * find 가 달라도 발전소가 다르면 연결하지 않는다
 * 모든 edge 를 연결해서 비게하면 끝낸다  
 * 
 * 2. 시간복잡도
 * edges 에 삽입 -> M
 * 꺼내서 연결한다 -> MlogN = 100000 * log1000
 * 
 * 3. 작업흐름
 * parent, union, find
 * class algorithm.binarysearch.Edge -> int from,  int to, int yny(발전소), int weight
 * find 해서 가장 상위의 노드에 발전소를 기록한다
 * 스패닝 트리를 만들 때: 같은 스패닝 트리일 경우 continue, find 해서 가장 상위 노드의 발전소랑 다르면 continue
 * 
 */

public class BOJ10423 {

	static class Node {
		int node;
		int yny;

		public Node (int node) {
			this.node = node;
			this.yny = 0;
		}

		public Node (int node, int yny) {
			this.node = node;
			this.yny = yny;
		}
	}

	static class Edge implements Comparable<Edge> {
		Node from;
		Node to;
		int weight;

		public Edge (Node from, Node to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}

	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	private static int K;
	
	private static final PriorityQueue<Edge> edges = new PriorityQueue<>();
	private static Node[] nodes;
	private static int[] parent;
	
	
	public static void main(String[] args) throws IOException {
		
		//input 세팅
		setInput();
		
		//스패닝트리 만들며 최소비용 출력
		System.out.println(makeMST());
	
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
		
		if (nodes[y].yny != 0 && nodes[x].yny != 0) {
			if (nodes[y].yny != nodes[x].yny) {
				return false;
			}
		}
		
		if (nodes[y].yny != 0 && nodes[x].yny == 0) {
			nodes[x].yny = nodes[y].yny;
		} else if (nodes[y].yny == 0 && nodes[x].yny != 0) {
			nodes[y].yny = nodes[x].yny;
		}
		
		if (y < x) {
			parent[x] = y; 
		} else if (y > x) {
			parent[y] = x;
		}

		return true;
	}
	
	private static void setInput() throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nodes = new Node[N+1];
		
		for (int i=1; i<N+1; i++) {
			nodes[i] = new Node(i);
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<K; i++) {
			int plantNumber = Integer.parseInt(st.nextToken());
			nodes[plantNumber].yny = plantNumber;
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
		
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(nodes[from], nodes[to], weight));
		}
		
		parent = new int[N+1];
		for (int i=1; i<N+1; i++) {
			parent[i] = i;
		}
	}
	
	
	private static int makeMST() {
		int totalWeight = 0;
		
		while (!edges.isEmpty()) {
			Edge nowEdge = edges.poll();
			if (union(nowEdge.from.node, nowEdge.to.node)) {
				totalWeight += nowEdge.weight;
			}
		}
		
		return totalWeight;
	}
	
}

