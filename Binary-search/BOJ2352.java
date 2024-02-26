/**
 * 1. 아이디어
 * 연결선이 교차하지 않도록 최대 몇 개까지 연결할 수 있는지 구하시오!
 * 
 * 2. 알고리즘
 * 연결선이 도착하는 숫자가 오름차순이 되어야 한다 -> 최대 증가하는 부분수열 문제이다
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int[] port;
	
	private static List<Integer> LIS;
	

    public static void main(String args[]) throws IOException  {
    	getInput();
    	
    	for (int i=0; i<N; i++) {
    		
    		if (LIS.isEmpty() || LIS.get(LIS.size() - 1) < port[i]) {
    			LIS.add(port[i]);
    		} else {
    			int index = binarySearch(port[i]);
    			LIS.set(index, port[i]);
    		}
    		
    	}
    	
    	System.out.println(LIS.size());


    }
    
    private static void getInput() throws IOException {
    	N = Integer.parseInt(br.readLine());
    	st = new StringTokenizer(br.readLine());
    	port = new int[N];
    	for (int i=0; i<N; i++) {
    		port[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	LIS = new ArrayList<>();
    }
    
    private static int binarySearch(int target) {
    	
    	int start = -1;
    	int end = LIS.size();
    	
    	while (start + 1 < end) {
    		int mid = (start + end) / 2;
    		
    		if (LIS.get(mid) < target) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	return end;
    }
    

}
