

/**
 * 1. 아이디어
 * 최대한 빨리 넥서스가 있는 곳으로 간다.
 * 0 ~ N-1 분기점.
 * 모든 분기점을 갈 수 있는 건 아님. 안보이는 곳만 갈 수 있다.
 * N-1 까지 최소시간으로 도달하라.
 * 
 * 2. 알고리즘 및 시간복잡도
 * 그래프 이론 -> 최단거리 구하기 -> 다익스트라
 * 
 * 
 * 3. 작업흐름
 * 
 * 다익스트라 내에서
 * weight가 작은순으로 대입하되, 방문했거나, 시야에 보인다면 불가.
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int node;
	long weight;
	
	public Node(int node, long weight) {
		this.node = node;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Node o) {
		return (int) (this.weight - o.weight);
	}
}

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N, M;
	private static boolean[] wards;
	private static List<Node>[] graph;
	private static long[] distances;
	private static boolean[] isVisit;
	
	private static Long INF = Long.MAX_VALUE;
	

	public static void main(String[] args) throws IOException, Exception {
		setInput();
		getMinTime();
		long answer = distances[N-1] == INF ? -1 : distances[N-1];
		System.out.println(answer);
	}
	
	private static void setInput() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		wards = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			if (Integer.parseInt(st.nextToken()) == 1) {
				wards[i] = true;
			}
		}
		
		graph = new List[N];
		for (int i=0; i<N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}
		
		distances = new long[N];
		for (int i=0; i<N; i++) {
			distances[i] = INF;
		}
		isVisit = new boolean[N];
	}
	
	private static void getMinTime() {
		PriorityQueue<Node> nodes = new PriorityQueue<>();
		nodes.add(new Node(0, 0));
		distances[0] = 0;
		
		while (!nodes.isEmpty()) {
			Node nowNode = nodes.poll();
			
			if (isVisit[nowNode.node]) {
				continue;
			}
			isVisit[nowNode.node] = true;
			
			for (Node nextNode : graph[nowNode.node]) {
				if (nextNode.node != N-1 && wards[nextNode.node]) {
					continue;
				}
				if (distances[nextNode.node] > nowNode.weight + nextNode.weight) {
					distances[nextNode.node] = nowNode.weight + nextNode.weight;
					nodes.add(new Node(nextNode.node, distances[nextNode.node]));
				}
			}
		}
	}
}
