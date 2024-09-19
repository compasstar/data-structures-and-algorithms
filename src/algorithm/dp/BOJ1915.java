package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1915 {
	
	private static int[][] arr;
	
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int M = Integer.parseInt(st.nextToken());

	    arr = new int[N][M];
	    for (int j=0; j<N; j++) {
	    	String input = br.readLine();
	    	int arrIndex = 0;
	    	for (char c : input.toCharArray()) {
	    		arr[j][arrIndex] = (int)(c - '0');
	    		arrIndex++;
	    	}
	    }
	    
	    for (int j=0; j<N; j++) {
	    	for (int i=0; i<M; i++) {		
	    		if (arr[j][i] != 0) {
	    			calculateArea(j, i);
	    		}
	    	}
	    }
	    
	    int answer = 0;
	    for (int j=0; j<N; j++) {
	    	for (int i=0; i<M; i++) {
	    		answer = Math.max(answer, arr[j][i]);
	    	}
	    }

	    System.out.println(answer * answer);

	}
	
	private static void calculateArea(int y, int x) {
		if (y <= 0 || x <= 0) {
			return;
		}
		
		int minValue = Math.min(arr[y-1][x], arr[y][x-1]);
		minValue = Math.min(minValue, arr[y-1][x-1]);
		arr[y][x] = minValue + 1;
	}

}
