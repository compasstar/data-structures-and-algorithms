import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	
    public static void main(String args[]) throws Exception {
    	
    	int T = Integer.parseInt(br.readLine());
    	for (int t=1; t<T+1; t++) {
    		sb.append('#').append(t).append(' ').append(run()).append('\n');
    	}
    	System.out.println(sb);
    }
    
    private static int run() throws IOException {
    	int move = 0;
    	
    	st = new StringTokenizer(br.readLine());
    	int x1 = Integer.parseInt(st.nextToken());
    	int y1 = Integer.parseInt(st.nextToken());
    	int x2 = Integer.parseInt(st.nextToken());
    	int y2 = Integer.parseInt(st.nextToken());
    	
    	int diffY = Math.abs(y2 - y1);
    	int diffX = Math.abs(x2 - x1);
    	
    	if (diffY > diffX) {
    		move += diffX * 2;
    		int remain = diffY - diffX;
    		
    		// 1, 4, 5, 8, 9, 12, 13
    		if (remain % 2 == 1) {
    			move += (remain/2) * 4 + 1;
    		} else if (remain % 2 == 0) {
    			move += (remain/2) * 4;
    		}
    	} else {
    		move += diffY * 2;
    		int remain = diffX - diffY;
    		if (remain % 2 == 1) {
    			move += (remain/2) * 4 + 1;
    		} else if (remain % 2 == 0) {
    			move += (remain/2) * 4;
    		}
    	}
    	
    	return move;
    }
}
