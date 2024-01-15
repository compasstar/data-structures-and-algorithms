import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] stack;
	private static int size = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		stack = new int[N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			
			if (cmd.equals("push")) {
				push(Integer.parseInt(st.nextToken()));
			} else if (cmd.equals("pop")) {
				System.out.println(pop());
			} else if (cmd.equals("size")) {
				System.out.println(size());
			} else if (cmd.equals("empty")) {
				System.out.println(empty());
			} else if (cmd.equals("top")) {
				System.out.println(top());
			}
		}
	}
	
	private static void push(int item) {
		stack[size] = item;
		size++;
	}
	
	private static int pop() {
		if (size == 0) {
			return -1;
		}
		int result = stack[size - 1];
		stack[size - 1] = 0;
		size--;
		return result;
	}
	
	private static int size() {
		return size;
	}
	
	private static int empty() {
		if (size == 0) {
			return 1;
		}
		return 0;
	}
	
	private static int top() {
		if (size == 0) {
			return -1;
		}
		return stack[size - 1];
	}
}
