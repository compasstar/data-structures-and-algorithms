"""
백준 14889번

1. 아이디어
- 백트래킹을 통해 순열 팀을 구성한다
- 구성된 팀의 점수를 매겨서 비교한다

2. 시간복잡도
- O(N!)

3. 자료구조
- S = int[][]

"""
import sys
input = sys.stdin.readline

N = int(input())
n = N/2

S = [list(map(int, input().split())) for _ in range(N)]
team_start = []
min_value = 100


def team_value(team):
    result = 0
    for j in team:
        for i in team:
            result += S[j][i]
    return result


def recur(num):
    global min_value

    if num == n:
        team_link = [x for x in range(N) if x not in team_start]
        min_value = min(min_value, abs(team_value(team_start) - team_value(team_link)))
        return

    for i in range(N):
        if len(team_start)==0 or i > team_start[-1]:
            team_start.append(i)
            recur(num+1)
            team_start.pop()


recur(0)
print(min_value)
