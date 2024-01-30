import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int N;
	private static int M;
	private static HashSet<String>[] hashSet = new HashSet[501];
	private static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<501; i++) {
			hashSet[i] = new HashSet<>();
		}
		
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			StringBuilder sb = new StringBuilder();
			for (int j=0; j<s.length(); j++) {
				sb.append(s.charAt(j));
				hashSet[j+1].add(sb.toString());
			}
		}
		
		for (int i=0; i<M; i++) {
			String s = br.readLine();
			if (hashSet[s.length()].contains(s)) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	

}
