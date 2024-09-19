package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class SWEA1208
{
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


	public static void main(String[] args) throws IOException {
		for (int i=1; i<11; i++) {
			System.out.println("#" + i + " " + getHeightDiffer());
		}
	}
	
	private static int getHeightDiffer() throws IOException {
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int dump = Integer.parseInt(st.nextToken());
		
		int[] box = new int[100];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<100; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<dump; i++) {
			Arrays.sort(box);
			box[99] -= 1;
			box[0] += 1;
		}
		
		Arrays.sort(box);
		return box[99] - box[0];
	}
}
