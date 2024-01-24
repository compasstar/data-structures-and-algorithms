import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * 1. 아이디어
 * 맵 바깥 한 칸 아래에 성 존재
 * 성에 궁수 3명 배치. y축 거리 D이하인 가장 가까운 적을 공격한다. 거리가 동일하다면 가장 왼쪽의 적을 공격.
 * 턴제 게임: 궁수공격 -> 적 한 칸 아래로 -> 궁수공격 -> 적 한 칸 아래로 -> ...
 * 적은 공격받거나 성에 도착하면 제외됨.
 * 궁수의 공격으로 제거할 수 있는 적의 최대 수를 구하여라!!!
 * 
 * 핵심은 궁수가 동일한 적을 공격하지 않도록 만드는 것인 것 같지만...
 * 그냥 궁수 배치를 모든 경우의 수 (조합 Combination)으로 시행해보면 될 것 같다.
 * 행 M개 중에 3개 고르기 (조합 Combination) --> 백트래킹(DFS)

 * 
 * 2. 시간복잡도
 * 궁수 위치 고르기 백트래킹 : MC3 = M! / (M-3)! * 3! = 15! / 12! * 3! = 15*14*13 / 6 = 455
 * 정해진 궁수 위치에서 공격하여 적 해치우기 한 번 시뮬레이션: 모든 칸 탐색을 턴 만큼하니까 N*M*턴 = N*M*N = 15^3 = 3375
 * O(10^2 * 10^3) = O(10^5) 가능!!!
 * 
 * 3. 작업흐름
 * int[N][M] map
 * int N; 열
 * int M; 행
 * int D; 사정거리
 * int maxEnemyAttack = 0;
 * List<Point> enemies 적 위치기록
 * int enemyExist
 * 
 * boolean[N] selectedBowMaster : 선택된 궁수들. 백트래킹을하며 이곳에서 선택되었다가 안되었다가 반복한다.
 * 
 * private static void 궁수 위치 고르기 백트래킹(궁수인원, 궁수시작인덱스): 
 * 	궁수인원이 3이라면 
 * 		calculateAttackEnemy()
 * 		return
 * 
 * 	for(int i=궁수시작인덱스; i<M; i++) {
 * 		궁수선택 selectedBowMaster[i] = true;
 * 		궁수위치고르기백트래킹(궁수인원+1, 궁수시작인덱스= i+1);
 * 		선택한궁수제외 selectedBowMater[i] = false;
 * 	}
 * 
 * selectedBowMater 기반으로 공격할 수 있는 적의 수를 계산하고 maxEnemy 를 max값으로 조정한다
 * private static int calculateAttackEnemy() {
 * 
 * 	맵 초기화. (적 위치 기록대로 1을 기록한다)
 * 	enemyExist = enemies.size() 남은 적 개수 초기화
 * 
 *  totalEnemyAttack = 0;
 * 	while (enemyExist > 0) 적이 남아있다면 
 * 		totalEnemyAttack += turn();
 * 	maxEnemyAttack = Math.max(maxEnemyAttack, totalEnemyAttack);
 * }
 * 
 * 
 * 한턴에 일어나는 일을 벌어진다. 궁수들은 D이내 적 중에 가장 가까운 적을 공격하고, 적들은 한 칸 밑으로 내려간다 (y+1)
 * private static int turn() {
 * 
 * 	궁수좌표1,2,3: 적 위치랑 궁수좌표 비교하며 가장 가까운 적 중 가장 왼쪽 적을 찾는다. 그 좌표를 0으로 바꾼다, 적 공격카운트 + 1
 * 	적들 좌표를 전부 한 칸 밑으로 내린다. 만약 맨 아래까지 간다면 0으로 바꾼다
 * 
 *	적을 잡거나 적이 맨 밑까지 내려오면 enemyExist--
 * 
 * 	return 공격카운트 
 */

class Point {
	int y;
	int x;
	
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	
	private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	private static int D;
	
	private static int maxEnemyAttack = 0; // 잡은 적의 최대 수: answer
	private static final List<Point> enemies = new ArrayList<>();
	private static List<Point> tempEnemies = new ArrayList<>(); //게임 진행 중일 때 필요한 임시 적의 좌표
	private static int enemyExist; //남은 적의 수. 궁수 공격뿐 아니라 성에 도착해도 --로 카운트한다
	private static boolean[] selectedBowMaster; // 선택된 궁수들 true라면 선택된 것임
	
	
	public static void main(String[] args) throws IOException {
		//1. 입력 받기
		setInput();
		
		//2. 백트래킹을 통해 궁수 고르기 -> 3명 고르면 게임 진행하고 제거 가능한 최대 수를 구한다
		selectBowMaster(0, 0);
		
		//3. 정답출력
		System.out.println(maxEnemyAttack);
	}
	
	private static void setInput() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		selectedBowMaster = new boolean[M];
		
		for (int j=0; j<N; j++) {
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<M; i++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					enemies.add(new Point(j, i));
				}
			}
		}
	}
	
	private static void selectBowMaster(int bowMasterNumber, int start) {
		if (bowMasterNumber == 3) {
			maxEnemyAttack = Math.max(maxEnemyAttack, calculateAttackEnemy()); //Answer 계산
			return;
		}
		
		for (int i=start; i<M; i++) {
			selectedBowMaster[i] = true;
			selectBowMaster(bowMasterNumber + 1, i + 1);
			selectedBowMaster[i] = false;
		}
	}
	
	private static int calculateAttackEnemy() {
		//맵 초기화 및 남은 적 수 초기화
		initiateMapAndEnemyExist();
		
		List<Point> bowMaster = new ArrayList<>();
		for (int i=0; i<M; i++) {
			if (selectedBowMaster[i] == true) {
				bowMaster.add(new Point(N, i)); //궁수의 좌표는 (N, i) 이다.
			}
		}
		
		int totalEnemyAttack = 0;
		while (enemyExist > 0) {
			totalEnemyAttack += turn(bowMaster);
		}

		return totalEnemyAttack;
	}
	
	//한 턴 동안 벌어지는 일 -> 궁수가 공격, 적이 한 칸 내려온다
	private static int turn(List<Point> bowMaster) {
		
		//궁수가 공격한 적의 리스트. 궁수들은 동일한 적을 공격할 수 있으므로, 중복이 허용되지 않은 Set 사용
		Set<Point> allMinEnemy = new HashSet<>(); 
		
		//궁수 공격
		for (Point b : bowMaster) {
			int minDistance = Integer.MAX_VALUE;
			Point minEnemy = null;
			
			for (Point e : tempEnemies) {
				int distance = (b.y - e.y) + Math.abs(b.x - e.x);
				if (distance <= D && minDistance > distance) {
					minDistance = distance;
					minEnemy = e;
				} else if (distance <= D && minDistance == distance) {
					if (e.x < minEnemy.x) {
						minDistance = distance;
						minEnemy = e;
					}
				}
			}
			if (minDistance != Integer.MAX_VALUE) {
				allMinEnemy.add(minEnemy);
			}
		}
		
		//공격 받은 적은 제거 된다
		for (Point p : allMinEnemy) {	
			tempEnemies.remove(p);
			enemyExist--;
		}
		
		//적들은 한 칸 씩 내려온다
		List<Point> removeList = new ArrayList<>();
		for (Point p : tempEnemies) {			
			p.y += 1;
			if (p.y == N) {
				removeList.add(p);
			}
		}
		
		//성벽까지 이동한 적들은 제거된다
		for (Point p : removeList) {
			tempEnemies.remove(p);
			enemyExist--;
		}

		return allMinEnemy.size();
	}
	
	private static void initiateMapAndEnemyExist() {
		tempEnemies = new ArrayList<>();
		for (Point p : enemies) {
			tempEnemies.add(new Point(p.y, p.x));
		}
		enemyExist = enemies.size();
	}
}

