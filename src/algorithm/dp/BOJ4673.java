package algorithm.dp;

public class BOJ4673 {


	public static void main(String[] args) {
		
		boolean[] notSelfNumber = new boolean[10001];
		
		for (int i=1; i<=10000; i++) {
			int d = d(i);
			if (d <= 10000) {
				notSelfNumber[d] = true;
			}
		}
		
		for (int i=1; i<=10000; i++) {
			if (!notSelfNumber[i]) {
				System.out.println(i);
			}
		}
		


	}
	
	private static int d(int num) {
		int sum = num;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}


}
