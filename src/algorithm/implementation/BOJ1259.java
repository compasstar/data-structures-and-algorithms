package algorithm.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1259 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		
		while (true) {
			String input = br.readLine();
			if (Integer.parseInt(input) == 0) {
				break;
			}
			
			if (isPalindrom(input.toCharArray())) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}
	
	private static boolean isPalindrom(char[] number) {
		int p1 = 0;
		int p2 = number.length - 1;
		
		while (p1 < p2) {
			if (number[p1++] != number[p2--]) {
				return false;
			}
		}
		return true;
	}
}
