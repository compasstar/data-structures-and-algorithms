import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17413번 단어 뒤집기 2
 * 
 * [1. 아이디어]
 * 문자열에서 단어만 뒤집는다
 * 소문자, 숫자, 공백, 꺽새 < >
 * 꺽새는 <> 세트로 등장
 * 
 * 꺽새 안은 무시 / 단어만 뒤집는다!
 * 
 * 1. 단어인지 꺽새인지 확인
 * 문자열을 돌면서 알파벳이나 숫자라면 단어 시작. 공백이나 꺽새 나오기 전까지 단어.
 * 뒤집기 메서드 실행
 * 
 * 꺽새나오면 태그 시작. 꺽새 끝나면 태그 끝.
 * 
 * 2. 단어라면 뒤집기 메서드
 * 파라미터로 시작 인덱스, 끝 인덱스 받는다
 * 시작 인덱스 ++, 끝 인덱스 -- 해가며 서로의 위치를 변경
 * 시작 인덱스 >= 끝 인덱스 라면 종료
 * 
 * !! 주의점
 * 문자열 바꿀 때 마지막에 공백문자 하나 넣기
 * 
 * 
 * [2. 시간복잡도]
 * 문자열 S = 100,000
 * 문자열을 돈다 => S
 * 문자열을 뒤집는다 => S
 * O(S)
 */


public class Main {


	private static char[] S;
	private static int size;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		size = input.length();
		S = new char[size + 1];
		for (int i=0; i<size; i++) {
			S[i] = input.charAt(i);
		}
		S[size] = ' ';
		
		
		int start = -1;
		int end = -1;
		int tagSwitch = 0;
		for (int i=0; i<size+1; i++) {
			if (S[i] == '>') {
				tagSwitch = 0;
			}
			
			if (tagSwitch == 1) {
				continue;
			}
			
			if (S[i] == '<') {
				tagSwitch = 1;
			}
			
			
			if (start == -1 && checkWord(S[i])) {
				start = i;
			} else if (start != -1 && !checkWord(S[i])) {
				end = i;
				reverseWord(start, end-1);
				start = -1;
				end = -1;
			}
		}
		
		
		for (int i=0; i<size; i++) {
			System.out.print(S[i]);
		}
	}
	
	private static boolean checkWord(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
			return true;
		}
		return false;
	}
	
	private static void reverseWord(int start, int end) {
		while (start < end) {
			char temp = S[start];
			S[start] = S[end];
			S[end] = temp;
			start++;
			end--;
		}
	}
}

