import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
	private static int maxValue;
	private static int[] numbers;
	private static int size;
	private static int changeCount;
	
	private static boolean dp[][]; //count, 현재 숫자
	
    public static void main(String args[]) throws NumberFormatException, IOException{
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t=1; t<T+1; t++) {
    		run();
    		sb.append('#').append(t).append(' ').append(maxValue).append('\n');
    	}
    	System.out.println(sb);
    }
    
    private static void run() throws IOException {
    	 getInput();
    	 dfs(0);
    }
    
    private static void getInput() throws IOException {
    	st = new StringTokenizer(br.readLine());
    	char[] numberString = st.nextToken().toCharArray();
    	size = numberString.length;
    	numbers = new int[size];
    	for (int i=0; i<size; i++) {
    		numbers[i] = numberString[i] - '0';
    	}  	
    	changeCount = Integer.parseInt(st.nextToken());
	   	maxValue = 0;   	
	   	dp = new boolean[11][1000000];
    }
    
    private static void dfs(int count) {
    	int number = changeArrayToInteger();

    	if (count == changeCount) {		
    		maxValue = Math.max(maxValue, number);
    		return;
    	}
    	
    	for (int j=0; j<size; j++) {
    		for (int i=j+1; i<size; i++) {
    			change(j, i);
    			
    			if (dp[count + 1][changeArrayToInteger()]) {
    				change(j, i);
    				continue;
    			}
    			
    			dp[count + 1][changeArrayToInteger()] = true;
    			dfs(count + 1);
    			change(i, j);
    		}
    	}
    }
    
    private static int changeArrayToInteger() {
    	int sum = 0;
    	for (int i=0; i<size; i++) {
    		sum += numbers[size - 1 - i] * Math.pow(10, i);
    	}
    	return sum;
    }

    private static void change(int index1, int index2) {
    	int temp = numbers[index1];
    	numbers[index1] = numbers[index2];
    	numbers[index2] = temp;	
    }  
}
