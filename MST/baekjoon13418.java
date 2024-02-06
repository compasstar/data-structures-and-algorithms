import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * - 오르막길: 점선, 내리막길: 실선
 * - 모든 건물 방문, 최소 길이 -> 미니멈스패닝트리
 * - 피로도를 가장 큰 경로와 가장 작은 경로의 차이를 구하시오!
 * 
 * - 유니온 파인드로 오르막길만 잇는 경로, 내리막길만 잇는 경로로 각각 만들어 피로도를 계산한디
 * 
 * 2. 시간복잡도
 * 
 * 3. 작업흐름
 * parent
 * union
 * find
 * N, M
 */

class EdgeDecrease implements Comparable<EdgeDecrease>{
	int from;
	int to;
	int weight;
	
	public EdgeDecrease(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(EdgeDecrease o) {
		return o.weight - this.weight;
	}
}

class EdgeIncrease implements Comparable<EdgeIncrease>{
	int from;
	int to;
	int weight;
	
	public EdgeIncrease(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(EdgeIncrease o) {
		return this.weight - o.weight;
	}
}

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int[] parent;
	private static int N;
	private static int M;
	
	private static int maxK = 0;
	private static int minK = 0;

	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		
		PriorityQueue<EdgeIncrease> queueIncrease = new PriorityQueue<>();
		PriorityQueue<EdgeDecrease> queueDecrease = new PriorityQueue<>();
		for (int i=0; i<M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int road = Integer.parseInt(st.nextToken()); //내리막길 1, 오르막길 0
			queueIncrease.add(new EdgeIncrease(from, to, road));
			queueDecrease.add(new EdgeDecrease(from, to, road));
		}
		
		parent = new int[N+1];
		for (int i=0; i<N+1; i++) {
			parent[i] = i;
		}
		while (!queueIncrease.isEmpty()) {
			EdgeIncrease edge = queueIncrease.poll();
			if (find(edge.from) == find(edge.to)) {
				continue;
			}
			union(edge.from, edge.to);
			
			if (edge.weight == 0) {
				maxK++;
			}
		}
		
		parent = new int[N+1];
		for (int i=0; i<N+1; i++) {
			parent[i] = i;
		}
		while (!queueDecrease.isEmpty()) {
			EdgeDecrease edge = queueDecrease.poll();
			if (find(edge.from) == find(edge.to)) {
				continue;
			}
			union(edge.from, edge.to);
			if (edge.weight == 0) {
				minK++;
			}
		}

		System.out.println((int)(Math.pow(maxK, 2) - Math.pow(minK, 2)));
	}
	
	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return find(parent[x]);
	}
	
	private static void union(int y, int x) {
		y = find(y);
		x = find(x);
		
		if (y < x) {
			parent[x] = y;
		} else if (x < y) {
			parent[y] = x;
		}
	}
	

}
