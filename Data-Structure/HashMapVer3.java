//HashMap ver3 (final)
//백준 1620번 나는야 포켓몬 마스터

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	
	String key;
	String value;
	Node next;
	
	public Node(String key, String value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
}

/**
 * HashCode 만드는 코드 암기 (getHashCodeIndex)
 * 해시테이블의 사이즈는 크기가 큰 소수로 하는 것이 시간단축 된다 ==> 10,007, 400,009
 */
class HashMap {
	
	private Node[] table;
	private int size;
	
	public HashMap(int size) {
		this.table = new Node[size];
		this.size = size;
	}
	
	private int getHashCodeIndex(String key) {
		int hash = 5381;
		for (char c : key.toCharArray()) {
			hash = ((hash << 5) + hash) + (int) c;
		}
		if (hash < 0) {
			hash *= -1;
		}
		return hash % size;
	}
	
	public void put(String key, String value) {
		int index = getHashCodeIndex(key);
		
		if (table[index] == null) {
			table[index] = new Node(key, value);
			return;
		}
		
		Node currentNode = table[index];
		if (currentNode.key.equals(key)) {
			currentNode.value = value;
			return;
		}
		
		while (currentNode.next != null && !currentNode.next.key.equals(key)) {
			currentNode = currentNode.next;
		}
		
		currentNode.next = new Node(key, value);
	}
	
	public String get(String key) {
		int index = getHashCodeIndex(key);
		
		if (table[index] == null) {
			return null;
		}
		
		Node currentNode = table[index];
		while (currentNode != null && !currentNode.key.equals(key)) {
			currentNode = currentNode.next;
		}
		return currentNode.value;
	}
}


public class Main {
	
	private static int N;
	private static int M;
	private static final int MAX_SIZE = 400009;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		HashMap hashMap = new HashMap(MAX_SIZE);
		HashMap hashMapReverse = new HashMap(MAX_SIZE);
		
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			String value = st.nextToken();
			hashMap.put(i + "", value);
			hashMapReverse.put(value, i + "");
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String key = st.nextToken();
			
			if (key.charAt(0) >= 'A' && key.charAt(0) <= 'Z') {
				sb.append(hashMapReverse.get(key)).append('\n');
			} else {
				sb.append(hashMap.get(key)).append('\n');
			}
		}
		System.out.println(sb);
	}
}
