import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int id;
	int distance;
	
	public Node(int id, int distance) {
		this.id = id;
		this.distance = distance;
	}

	@Override
	public int compareTo(Node o) {
		return this.distance - o.distance;
	}
	
}

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N, D;
	private static List<Node>[] graph;
	private static boolean[] isVisit;
	private static int[] dist;
	
	public static void main(String[] args) throws IOException {
		
		setInput();
		
		int answer = getMinDistance();
		System.out.println(answer);

	}
	
	private static void setInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[D+1];
		for (int i=0; i<D+1; i++) {
			graph[i] = new ArrayList<>();
			graph[i].add(new Node(i+1, 1));
		}
		graph[D].clear();
		
		isVisit = new boolean[D+1];
		dist = new int[D+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			if (end > D) {
				continue;
			}
			
			graph[start].add(new Node(end, distance));
		}
	}
	
	private static int getMinDistance() {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));
		dist[0] = 0;
		
		while (!pq.isEmpty()) {
			Node nowNode = pq.poll();
			
			if (isVisit[nowNode.id]) {
				continue;
			}
			isVisit[nowNode.id] = true;
			
			for (Node nextNode : graph[nowNode.id]) {
				if (dist[nextNode.id] > dist[nowNode.id] + nextNode.distance) {
					dist[nextNode.id] = dist[nowNode.id] + nextNode.distance;
					pq.add(new Node(nextNode.id, dist[nextNode.id]));
				}
			}
		}
		
		return dist[D];
	}

}

