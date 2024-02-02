import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Node{
    Set<Node> nexts = new HashSet<>(); // 중복되어 들어오므로 set으로 구현
    int value;
    int cnt = 0; //초기값은 0
    
    
    Node() {
    	
    }

    Node(int value,int cnt){
        this.value = value;
        this.cnt =cnt;
    }
}

class Solution
{
    static Node[] nodes;
    static int answer;
    static boolean isVisit[];
    static int maxCnt;

    static void bfs(Node node) {
    	
    	Queue<Node> queue = new LinkedList<>();
    	queue.add(node);
    	
    	while (!queue.isEmpty()) {
    		Node nowNode = queue.poll();
    		for (Node nextNode : nowNode.nexts) {
    			if (isVisit[nextNode.value]) {
    				continue;
    			}
    			isVisit[nextNode.value] = true;
    			nextNode.cnt = nowNode.cnt + 1;
    			queue.add(nextNode);

    			if (nextNode.cnt > maxCnt) {
    				maxCnt = nextNode.cnt;
    				answer = nextNode.value;
    			} else if (nextNode.cnt == maxCnt && nextNode.value > answer) {
    				answer = nextNode.value;
    			}
    		}
    	}
    }



	public static void main(String args[]) throws Exception
	{
	    
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	    StringTokenizer st;
	    for(int test_case = 1; test_case <= 10; test_case++)
	    {
	        nodes = new Node[101];
	        answer =0;
	        isVisit = new boolean[101];
	        
	        maxCnt =0;
	        st = new StringTokenizer(reader.readLine(), " ");
	        int N = Integer.parseInt(st.nextToken());
	        int start = Integer.parseInt(st.nextToken());
	
	        //노드 배열 초기화
	        for (int i=0; i<101; i++) {
	        	nodes[i] = new Node();
	        }
	        
	        st = new StringTokenizer(reader.readLine(), " ");
	        
	        for (int i=0; i<N/2; i++) {
	        	int from = Integer.parseInt(st.nextToken());
	            int to = Integer.parseInt(st.nextToken());
	            nodes[from].value = from;
	            nodes[to].value = to;
	            nodes[from].nexts.add(nodes[to]);
	        }
	        
	        isVisit[nodes[start].value] = true;
	        bfs(nodes[start]);
	        System.out.println("#" + test_case + " " + answer);
	    }
	}

}
