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

class LinkedList {
	Node head;
	
	public LinkedList() {
		this.head = null;
	}
	
	public void add(String key, String value) {
		Node newNode = new Node(key, value);
		
		if (head == null) {
			this.head = newNode;
		} else {
			Node tempNode = head;
			while (tempNode.next != null) {
				tempNode = tempNode.next;
			}
			tempNode.next = newNode;
		}
	}
}

/**
 * HashCode 만드는 코드 암기 (getHashCodeIndex)
 * 해시테이블의 사이즈는 크기가 큰 소수로 하는 것이 시간단축 된다 ==> 10,007, 400,009
 */
class HashMap {
	
	LinkedList[] table;
	int size;
	
	public HashMap(int size) {
		table = new LinkedList[size];
		this.size = size;
	}
	
	public int getHashCodeIndex(String key) {
		int hash = 5381;
		for (char c : key.toCharArray()) {
			hash = ((hash << 5) + hash) + (int)c;
		}
		if (hash < 0) {
			hash *= -1;
		}
		return hash % size;
	}
	
	public void put(String key, String value) {
		int index = getHashCodeIndex(key);
		
		if (table[index] == null) {
			table[index] = new LinkedList();
			table[index].add(key, value);
			return;
		}
		
		Node tempNode = table[index].head;
		while (tempNode != null) {
			if (tempNode.key.equals(key)) {
				tempNode.value = value;
				return;
			}
			tempNode = tempNode.next;
		}
		table[index].add(key, value);
		return;
	}
	
	public String get(String key) {
		int index = getHashCodeIndex(key);
		
		if (table[index] == null) {
			return null;
		} 
		
		Node tempNode = table[index].head;
		while (tempNode != null) {
			if (tempNode.key.equals(key)) {
				return tempNode.value;
			}
			tempNode = tempNode.next;
		}
		return null;
	}
}



public class Main {

	private static int N;
	private static int M;
	private static int MAX_SIZE = 400009;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		HashMap hashMap = new HashMap(MAX_SIZE);
		HashMap hashMapReverse = new HashMap(MAX_SIZE);

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			hashMap.put(i + "", input);
			hashMapReverse.put(input, i + "");
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String input = st.nextToken();
			if (input.charAt(0) >= 'A' && input.charAt(0) <= 'Z') {
				answer.append(hashMapReverse.get(input)).append('\n');
			} else {
				answer.append(hashMap.get(input)).append('\n');
			}
		}

		System.out.println(answer);
	}
}
