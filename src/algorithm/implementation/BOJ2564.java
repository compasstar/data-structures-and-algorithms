package algorithm.implementation; /**
 * 동근이의 위치와 각 상점 사이의 최단 거리의 합을 구하시오!
 * 
 * 1: 북 2: 남 3: 서: 4: 동
 * 북,남 --> 위쪽으로부터 거리
 * 동,서 --> 왼쪽으로부터 거리
 * 
 * 상점, 동근이는 꼭짓점에 있지 않다
 */

//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class BOJ2564 {

	static class Place {
		int direction;
		int distance;

		public Place(int direction, int distance) {
			this.direction = direction;
			this.distance = distance;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	private static int N;
	private static int M;
	private static int shopNumber;
	private static Place[] shops;
	private static Place user;

	private static int totalDistance;



	public static void main(String[] args) throws IOException  {
		
		getInput();
		
		for (Place shop : shops) {
			totalDistance += getDistance(shop);
		}
		
		System.out.println(totalDistance);
	}
	
	private static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로
		shopNumber = Integer.parseInt(br.readLine()); // 상점 개수
		shops = new Place[shopNumber];
		
		for (int i=0; i<shopNumber; i++) {
			st = new StringTokenizer(br.readLine());
			int direction = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			shops[i] = new Place(direction, distance);
		}
		st = new StringTokenizer(br.readLine());
		int direction = Integer.parseInt(st.nextToken());
		int distance = Integer.parseInt(st.nextToken());
		user = new Place(direction, distance);
		
		totalDistance = 0;
	}
	
	private static int getDistance(Place shop) {
		
		//북쪽
		if (user.direction == 1) {
			
			if (shop.direction == 1) {
				return Math.abs(user.distance - shop.distance); 
			} else if (shop.direction == 2) {
				return N + Math.min(user.distance + shop.distance, M - user.distance + M - shop.distance);
			} else if (shop.direction == 3) {
				return user.distance + shop.distance;
			} else if (shop.direction == 4) {
				return M - user.distance + shop.distance;
			}
			
		} else if (user.direction == 2) { //남쪽
			
			if (shop.direction == 1) {
				return N + Math.min(user.distance + shop.distance, M - user.distance + M - shop.distance);
			} else if (shop.direction == 2) {
				return Math.abs(user.distance - shop.distance); 
			} else if (shop.direction == 3) {
				return user.distance + N - shop.distance;
			} else if (shop.direction == 4) {
				return M - user.distance + N - shop.distance;
			}
			
			
		} else if (user.direction == 3) { //서쪽
			
			if (shop.direction == 1) {
				return user.distance + shop.distance;
			} else if (shop.direction == 2) {
				return N - user.distance + shop.distance;
			} else if (shop.direction == 3) {
				return Math.abs(user.distance - shop.distance); 
			} else if (shop.direction == 4) {
				return M + Math.min(user.distance + shop.distance, N - user.distance + N - shop.distance);
			}
			
		} else if (user.direction == 4) { //동쪽
			
			if (shop.direction == 1) {
				return user.distance + M - shop.distance;
			} else if (shop.direction == 2) {
				return N - user.distance + M - shop.distance;
			} else if (shop.direction == 3) {
				return M + Math.min(user.distance + shop.distance, N - user.distance + N - shop.distance);
			} else if (shop.direction == 4) {
				return Math.abs(user.distance - shop.distance); 
			}
			
		}
	
		return -1;	
	}
	


}
