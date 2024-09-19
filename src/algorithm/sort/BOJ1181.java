package algorithm.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BOJ1181 {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		List<String> s = new ArrayList<String>();
		for (int i=0; i<N; i++) {
			s.add(br.readLine());
		}
		Set<String> set = new HashSet<String>(s);
		List<String> newList = new ArrayList<String>(set);
		
		Collections.sort(newList, (o1, o2) -> {
			if (o1.length() != o2.length()) {
				return o1.length() - o2.length();
			} 
			return o1.compareTo(o2);
		});
		
		for (String w : newList) {
			System.out.println(w);
		}
	}
}
