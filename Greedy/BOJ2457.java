import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [프리랜서]
 * @author 이재홍
 * 
 * 1. 아이디어
 * 프로젝트 N개
 * 3/1 ~ 11/30 매일 한 가지 이상 프로젝트 참여
 * 이왕이면 참여하는 프로젝트 수 최소화
 * 
 * 2. 알고리즘
 * 
 * 달력을 만들고, 프로젝트를 참여하면 true로 변경한다
 * 백트래킹으로 각 프로젝트를 on, off 하면서 마지막까지 갔을 때, 조건충족하는지 체크하고 최소횟수를 초기화시킨다
 * on, off가 가능하니 -> 비트마스킹이 가능한가? 
 * 근데 백트래킹으로 하면 총 경우의 수가 2^N = 2^100,000 이기 때문에 시간초과 억세게 나네요...? 불가능!
 * 
 * 일단 시작점을 기준으로 정렬한다.
 * 3/1이하 시작점을 찾는다
 * 3/1이하 시작점 중에 가장 끝점이 늦은 것을 고른다
 * 그다음 끝점을 시작점으로 두고, 시작점 이하 중에 가장 끝점이 늦은 것을 고른다
 * 반복해서 끝점이 11/30보다 크다면 최솟값 구하고 종료.
 * O(N) 이 나옴. 한 번만 돌면된다.
 * 
 * 
 * 
 * 3. 작업흐름
 * 
 * PriorityQueue pq 에 모두 집어넣는다
 * 
 * int from
 * int to = 3/1
 * 
 * while !pq.isEmpty
 * pq.poll
 * 
 * 지금 from이 to보다 작고, 지금 to가 가장 늦은 날짜를 고른다
 * 만약 존재하지 않으면 break하고 0을 출력
 * 찾았으면 cnt + 1 하고 to 초기화
 * 
 * 
 *
 */

class Project implements Comparable<Project> {
	Day from;
	Day to;
	
	public Project(Day from, Day to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public int compareTo(Project o) {
		if (this.from.month < o.from.month) {
			return -1;
		} else if (this.from.month > o.from.month) {
			return 1;
		} else if (this.from.month == o.from.month) {
			if (this.from.day < o.from.day) {
				return -1;
			} else if (this.from.day > o.from.day) {
				return 1;
			} else {
				return 0;
			}
		}
		return 0;
	}
}

class Day implements Comparable<Day> {
	int month;
	int day;
	
	public Day(int month, int day) {
		this.month = month;
		this.day = day;
	}

	@Override
	public int compareTo(Day o) {
		
		if (this.month < o.month) {
			return 1;
		} else if (this.month > o.month) {
			return -1;
		} else if (this.month == o.month) {
			if (this.day < o.day) {
				return 1;
			} else if (this.day > o.day) {
				return -1;
			} else {
				return 0;
			}
		}
		
		return 0;
	}
}

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static Project[] projects;

	private static final Day finalDay = new Day(11, 30);

	public static void main(String[] args) throws NumberFormatException, IOException {
		getInput();
		
		int answer = countProject();
		System.out.println(answer);
	}
	
	private static void getInput() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		projects = new Project[N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int from1 = Integer.parseInt(st.nextToken());
			int to1 = Integer.parseInt(st.nextToken());
			Day day1 = new Day(from1, to1);
			
			int from2 = Integer.parseInt(st.nextToken());
			int to2 = Integer.parseInt(st.nextToken());
			Day day2 = new Day(from2, to2);
			
			projects[i] = new Project(day1, day2);
		}
	}
	
	private static int countProject() {
		
		int cnt = 0;
		
		PriorityQueue<Project> pq = new PriorityQueue<>();
		for (int i=0; i<N; i++) {
			pq.offer(projects[i]);
		}
		
		Day to = new Day(3, 1);
		Day maxTo = new Day(1, 1);
	
		while (!pq.isEmpty()) {
			
			maxTo = new Day(1, 1);
			
			//peek 이 to 보다 크다면 안 이어지므로 끝
			if (compareDay(pq.peek().from, to) < 0) {
				return 0;
			}
			
			//peek 이 to 보다 작다면
			while (!pq.isEmpty() && compareDay(pq.peek().from, to) >= 0) {
				Project nextProject = pq.poll();
				Day nowFrom = nextProject.from;
				Day nowTo = nextProject.to;

				//nowTo 가 maxTo 보다 크다면,  maxTo를 nowTo로 바꾸어준다
				if (compareDay(nowTo, maxTo) == -1) {
					maxTo = nowTo;
					
					if (compareDay(maxTo, finalDay) < 0) {	
						return (cnt + 1);
					}
				}
			}
			cnt++;		
			
			to = maxTo;
		}
		
		return 0;
	}
	
	/**
	 * d1 < d2 --> 1 반환
	 * d1 > d2 --> -1 반환
	 * d1 == d2 --> 0 반환
	 * 
	 */
	private static int compareDay(Day d1, Day d2) {
		
		if (d1.month < d2.month) {
			return 1;
		} else if (d1.month > d2.month) {
			return -1;
		} else if (d1.month == d2.month) {
			if (d1.day < d2.day) {
				return 1;
			} else if (d1.day > d2.day) {
				return -1;
			} else {
				return 0;
			}
		}
		
		return 0;
	}

}
