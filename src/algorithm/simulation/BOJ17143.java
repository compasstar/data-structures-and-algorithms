package algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 낚시왕은 오른쪽으로 한칸씩 이동한다
 * 낚시왕이 있는 열에서 가장 y 값이 낮은 상어를 잡는다.
 * 상어는 이동한다. 만약 상어가 경계선까지 간 경우, 방향을 반대로 바꿔서 이동한다
 * 상어는 한 칸에 두 마리 이상 있을 수 있다! 이 경우에는 크기가 가장 큰 상어만 살아남는다
 * 낚시왕이 잡은 상어 크기의 합을 구하시오!!!
 * 
 * 낚시왕 이동&캣치 -> 상어 움직임 -> 낚시왕 이동&캣치 -> 상어 움직임 -> ... -> 낚시왕 끝 도달 END
 * 
 * 상어의 움직임을 한 칸씩 움직이며 반복문을 돌면 시간초과가 된다. 수학적으로 계산해서 위치를 잡아주어야 한다.
 * 상어의 위치 = S(속력) % (R*2) --> 위치 + 이동거리 (만약 공간을 초과하면 차이만큼 반대로 이동 후, 방향 바꿔주기)
 * 
 * 2. 시간복잡도
 * 낚시왕 이동&캣치: R*C = 10^4
 * 상어 움직임: 턴 개수 * 상어 개수 = C * M * S = 100 * 10000 = 10^6 
 * 맵 돌면서 큰 상어만 남기기: R*C
 * 10^4 + 10^6 + 10^4 --> 가능
 * 
 * 3. 작업흐름
 * class algorithm.simulation.Shark: int id(인덱스역할), int r, int c, int s(속력), int d(방향), int z(크기)
 * int R, C, M
 * int[R][C] map 상어 id값이 들어있다
 * algorithm.simulation.Shark[M] sharks
 * int totalSharkSize = 0;
 * int kingX = -1;
 * 
 * main
 * while (kingX < C)
 * kingMove()
 * sharkMove()
 * 프린트 totalSharkSize
 * 
 * private void kingMove()
 * 	kingX++;
 *  for (i=0 ~ R)
 *  	만약 map이 비어있지 않다면 해당 상어 잡고 totalSharkSize 에 더해준다.
 *  
 * private void sharkMove()
 * 	상어 위치 = 상어 위치 % (R*2)
 * 	상어 위치 + 자기속력 --> 만약 공간 초과하면 반대로 이동 후 방향 바꾸기
 * 	만약 map에 이미 상어가 있으면 둘 중 작은 상어를 잡아먹는다. map에 대체해주고, 상어 리스트에서 빼준다
 * 	만약 상어가 없으면 map에서 위치를 바꾸어준다.
 * 
 */



public class BOJ17143 {

	static class Shark {
		int id;
		int r;
		int c;
		int s;
		int d;
		int z;

		public Shark(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Shark(int id, int r, int c, int s, int d, int z) {
			this.id = id;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int R;
	private static int C;
	private static int M;
	private static int[][] map;
	private static Shark[] sharks;
	private static int totalSharkSize;
	private static int kingX;

	
	public static void main(String[] args) throws IOException {
		getInput();
		
		while (kingX < C - 1) {
			kingMove();
			sharkMove();
		}
		
		System.out.println(totalSharkSize);
	}
	
	private static void getInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharks = new Shark[M + 1];
		map = new int[R][C];
		
		for (int i=1; i<M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks[i] = new Shark(i, r, c, s, d, z);
			map[r][c] = i;
		}
		
		totalSharkSize = 0;
		kingX = -1;
	}
	
	private static void kingMove() {
		kingX++;
		for (int i=0; i<R; i++) {
			if (map[i][kingX] != 0) {
				int sharkId = map[i][kingX];
				totalSharkSize += sharks[sharkId].z;
				map[i][kingX] = 0;
				sharks[sharkId] = null;
				break;
			}
		}
	}
	
	private static void sharkMove() {
		sharkMoveDirection();
		sharkMoveFight();
	}
	
	private static void sharkMoveDirection() {
		for (Shark shark : sharks) {
			if (shark == null) {
				continue;
			}
			
			if (shark.d == 1) {
				shark.r -= shark.s;
				if (shark.r < 0) {
					shark.r = shark.r * (-1);
					shark.r %= (R-1) * 2;
					shark.d = 2;
				}
				if (shark.r >= R - 1) {
					shark.r = (R - 1) - (shark.r - (R - 1));
					shark.d = 1;
				}
			} else if (shark.d == 2) {
				shark.r += shark.s;
				shark.r %= (R-1) * 2;
				if (shark.r >= R - 1) {
					shark.r = (R - 1) - (shark.r - (R - 1));
					shark.d = 1;
				}
				if (shark.r < 0) {
					shark.r = shark.r * (-1);
					shark.d = 2;
				}
			} else if (shark.d == 3) {
				shark.c += shark.s;
				shark.c %= (C-1) * 2;
				if (shark.c >= C - 1) {
					shark.c = (C - 1) - (shark.c - (C - 1));
					shark.d = 4;
				}
				if (shark.c < 0) {
					shark.c = shark.c * (-1);
					shark.d = 3;
				}
			} else if (shark.d == 4) {
				shark.c -= shark.s;
				if (shark.c < 0) {
					shark.c = shark.c * (-1);
					shark.c %= (C-1) * 2;
					shark.d = 3;
				}
				if (shark.c >= C - 1) {
					shark.c = (C - 1) - (shark.c - (C - 1));
					shark.d = 4;
				}
			}	
		}
	}
	
	private static void sharkMoveFight() {
		map = new int[R][C];
		for (Shark shark : sharks) {
			if (shark == null) {
				continue;
			}
			
			if (map[shark.r][shark.c] == 0) {
				map[shark.r][shark.c] = shark.id;
			} else {
				if (sharks[map[shark.r][shark.c]].z < shark.z) {
					int existSharkId = map[shark.r][shark.c];
					sharks[existSharkId] = null;
					map[shark.r][shark.c] = shark.id;
				} else if (sharks[map[shark.r][shark.c]].z > shark.z) {
					sharks[shark.id] = null;
				}
			}
		}
	}
}
