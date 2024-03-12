import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * N명 학생, X 마을 파티, M개의 단방향! 도로, 비용 T
 * 최단시간으로 파티에 갔다가 다시 원래 마을로 돌아온다
 * 단방향이기에 오고 가는 길이 다를 수 있음.
 * 오고 가는데 가장 큰 시간을 소비하는 학생을 구하시오!!!
 * 
 * 2. 알고리즘
 * 그래프 -> 최단거리 -> 다익스트라
 * (V + E)log(V) = 10,000
 * 각 마을마다 다익스트라를 2번 실행한다 -> 10,000 * 2* 1,000 = 10,000,000
 * 
 * 
 * 3. 작업흐름
 * 각 노드에서 연결된 엣지들을 ArrayList로 저장한다 List[] graph
 * 다익스트라로 출발지 ~ 도착지, 도착지 ~ 출발지 두번 실행하여 최단 소요시간을 저장해둔다. -> int[] totalTime
 * 
 */


class Node implements Comparable<Node> {
	int id;
	int w;
	
	public Node(int id, int w) {
		this.id = id;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		return this.w - o.w;
	}
}

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N, M, X; //노드, 엣지, 도착지
	private static List<Node>[] graph;
	private static int[] totalTime;


	public static void main(String[] args) throws IOException {
		setInput();
		for (int i=1; i<N+1; i++) {
			totalTime[i] = getTime(i, X) + getTime(X, i);
		}
		System.out.println(getMaxTotalTime());
	}
	
	private static void setInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for (int i=1; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		totalTime = new int[N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			graph[start].add(new Node(end, time));
		}
	}
	
	private static int getTime(int start, int end) {
		
		boolean[] isVisited = new boolean[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		distance[start] = 0;
		
		while (!pq.isEmpty()) {
			Node nowNode = pq.poll();
			if (isVisited[nowNode.id]) {
				continue;
			}
			isVisited[nowNode.id] = true;
			
			for (Node nextNode : graph[nowNode.id]) {
				if (distance[nextNode.id] > nowNode.w + nextNode.w) {
					distance[nextNode.id] = nowNode.w + nextNode.w;
					pq.add(new Node(nextNode.id, distance[nextNode.id]));
				}
			}
		}
		
		return distance[end];
	}
	
	private static int getMaxTotalTime() {
		int maxTotalTime = 0;
		for (int i=1; i<N+1; i++) {	
			maxTotalTime = Math.max(maxTotalTime, totalTime[i]);
		}
		return maxTotalTime;
	}
}
