
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int maxValue;
	private static int[] numbers;
	private static int size;
	
	private static int sw = 0;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder answer = new StringBuilder();
		for (int test_case = 1; test_case < T+1; test_case++) {
			answer.append('#').append(test_case).append(' ').append(getMaxPrice()).append('\n');
		}
		System.out.println(answer);
	}
	
	private static int getMaxPrice() throws IOException {
		
		st = new StringTokenizer(br.readLine());
		String numbersString = st.nextToken();
		
		maxValue = 0;
		size = numbersString.length();
		numbers = new int[size];
		for (int i=0; i<size; i++) {
			numbers[i] = numbersString.charAt(i) - '0';
		}
	
		int trade = Integer.parseInt(st.nextToken());
		
		dfs(trade);
		
		return maxValue;
	}
	
	private static void dfs(int trade) {
		if (trade == 0) {
			int nowNumber = changeNumbers();
			if (maxValue < nowNumber) {
				maxValue = nowNumber;
			}
			return;
		}
		
		
		/**
		 * 내림차순으로 정렬 된다면 sw = 1 하고 return 으로 끝낸다
		 * 남은 trade가 짝수라면 그대로 끝
		 * 남은 trade가 홀수라면 -> 중복된 숫자 있으면 그대로 끝 / 중복된 숫자 없으면 맨 뒤 두 숫자 교환 
		 */
		sw = 1;
		for (int i=0; i<size-1; i++) {
			if (numbers[i] < numbers[i+1]) {
				sw = 0;	
				break;
			}
		}
		if (sw == 1 && trade % 2 == 0) {
			maxValue = changeNumbers();
			return;
		} else if (sw == 1 && trade % 2 == 1) {
			
			int maxCount = 0;
			for (int i=0; i<size - 1; i++) {
				if (numbers[i] == numbers[i+1]) {
					maxCount = 1;
					break;
				}
			}
			if (maxCount == 0) {
				changeTwo(size - 1, size - 2);
			} 
			maxValue = changeNumbers();
			return;
		}
		sw = 0;
		
		for (int j=0; j<size; j++) {
			for (int i=j+1; i<size; i++) {
				changeTwo(j, i);
				dfs(trade - 1);
				changeTwo(i, j);
				
				if (sw == 1) {
					break;
				}
			}
			if (sw == 1) {
				break;
			}
		}
	}
	
	private static int changeNumbers() {
		String stringNumber = "";
		for (int i=0; i< size; i++) {
			stringNumber += numbers[i];
		}
		return Integer.parseInt(stringNumber);
	}
	
	private static void changeTwo(int i1, int i2) {
		int temp = numbers[i1];
		numbers[i1] = numbers[i2];
		numbers[i2] = temp;
	}
}

