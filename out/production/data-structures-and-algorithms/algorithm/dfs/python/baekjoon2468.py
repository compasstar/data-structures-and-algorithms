"""
백준 2468번

1. 아이디어
- 높이를 1부터 100까지 돌면서 안 잠긴 지역 측정
- 도는 것은 DFS로 돈다

2. 시간복잡도
- O(V+E) = 100 + 400 = 500
- 500 * 100 = 50000

3. 자료구조
- 지도 region = int[][]
- 방문여부 chk = bool[][]
- 안잠긴 지역 수 최대 number = int
"""

import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = int(input())

region = [list(map(int, input().split())) for _ in range(N)]
number = []


dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
def dfs(y, x, depth):

    for i in range(4):
        py = y + dy[i]
        px = x + dx[i]
        if 0<=py<N and 0<=px<N:
            if chk[py][px] == False and region[py][px] > depth:
                chk[py][px] = True
                dfs(py, px, depth)

for depth in range(0, 101):
    each = 0
    chk = [[False] * N for _ in range(N)]
    for j in range(N):
        for i in range(N):
            if chk[j][i] == False and region[j][i] > depth:
                chk[j][i] = True
                dfs(j, i, depth)
                each += 1

    number.append(each)


print(max(number))