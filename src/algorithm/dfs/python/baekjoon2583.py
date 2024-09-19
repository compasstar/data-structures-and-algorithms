"""
백준 2583번

1. 아이디어
- 좌표기준으로 지도를 그림그린다
- 2중 for문으로 DFS로 돌면서 넓이 저장

2. 시간복잡도
- N*M*O(V+E) = 100*100*(5N*M) = 10000 * 50000 = 500,000,000

3. 자료구조
- 지도 graph = int[][]
- 방문여부 chk = bool[][]
- 넓이 area = int[]
"""

import sys
sys.setrecursionlimit(10**6)

input = sys.stdin.readline

M, N, K = map(int, input().split())
graph = [[0]*N for _ in range(M)]
chk = [[False]*N for _ in range(M)]
area = []

for _ in range(K):
    x1, y1, x2, y2 = map(int, input().split())

    for j in range(y1, y2):
        for i in range(x1, x2):
            graph[j][i] = 1


dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
each = 0
def dfs(y, x):
    global each
    each += 1
    for i in range(4):
        py = y + dy[i]
        px = x + dx[i]
        if 0<=py<M and 0<=px<N:
            if chk[py][px] == False and graph[py][px] == 0:
                chk[py][px] = True
                dfs(py, px)

for j in range(M):
    for i in range(N):
        if chk[j][i] == False and graph[j][i] == 0:
            each = 0
            chk[j][i] = True
            dfs(j, i)
            area.append(each)

area.sort()
print(len(area))
for a in area:
    print(a, end = " ")