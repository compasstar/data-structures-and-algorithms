import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어 
 * 문제 예시 입력 출력과 맞지 않는다...
 * 루트 노드는 리프노드라고 합니다. 그리고 한 줄로 쭉 뻗어나가다가 n - m 에서 리프노드 개수만큼 다닥다닥 붙여주면 됩니다
 * 
 */



public class Main {
  
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Integer n = Integer.parseInt(st.nextToken());
		Integer m = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<n-1; i++) {
			if (i < n - m) {
				System.out.println(i + " " + (i + 1));
			} else {
				System.out.println(n - m + " " + (i + 1));
			}
		}
	}
}

