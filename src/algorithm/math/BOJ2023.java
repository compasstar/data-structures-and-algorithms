package algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * 1. 아이디어
 * 신기한 소수: 7331, 733, 73, 7 모든 자리수 이렇게 전부 소수인 경우
 * 에라토스테네스의 체: 
 * 25이하 소수 찾기
 * 2, 4, 6, ... 2의배수 버리고
 * 3, 6, 9, ... 3의 배수 버리고
 * 4, 8, 12, ... 4의 배수 버리고
 * 5, 10, 15, ... 5의 배수 버리고
 * 5^2 이므로, 5까지만 체크하면 된다
 * 
 * 일단 에라토스테네스의 체로 소수를 찾고
 * 소수 중에 각 자리도 전부 소수인 신기한 소수를 찾아서 출력한다
 */

public class BOJ2023 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static List<Integer> plusNumber = Arrays.asList(1, 3, 5, 7, 9);

    public static void main(String[] args) throws NumberFormatException, IOException {
    	N = Integer.parseInt(br.readLine());
    	List<Integer> primeArr = Arrays.asList(2, 3, 5, 7);
    	for (int num : primeArr) {
    		dfs(0, num);
    	}

    }
    
    private static void dfs(int depth, int number) {
    	if (depth == N-1) {
    		System.out.println(number);
    		return;
    	}
    	
    	for (int plus : plusNumber ) {
    		int nextNumber = number * 10 + plus;
    		if (isPrime(nextNumber)) {
    			dfs(depth + 1, nextNumber);
    		}
    	}
    }
    
    private static boolean isPrime(int number) {
    	if (number == 1) {
    		return false;
    	}
    	
    	for (int i=2; i<= (int)(Math.sqrt(number)); i++) {
    		if (number % i == 0) {
    			return false;
    		}
    	}
    	return true;
    }
}
