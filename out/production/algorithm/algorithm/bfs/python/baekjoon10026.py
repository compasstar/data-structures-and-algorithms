"""
1. 아이디어
- 반복문으로 지도를 돈다
- 현재 값이랑 동일하다면 BFS를 통해 돌고 구역 + 1

2. 시간복잡도
- 지도 돈다: NxN
- BFS: O(V+E) = NxN
-> O(N^4) = 100000000 = 1억

3. 자료구조
- 지도 chart
- 방문했는지 chk
- 구역 개수 region
- bfs
"""

import sys
import copy
from collections import deque

input = sys.stdin.readline
N = int(input())
chart = [input() for _ in range(N)]
chk = [[0] * N for _ in range(N)]
chkRG = copy.deepcopy(chk)
region = 0
regionRG = 0


def bfs(i, j):

    queue = deque()
    queue.append((i, j))
    character = chart[i][j]
    dy = [1, 0, -1, 0]
    dx = [0, 1, 0, -1]

    while(queue):
        y, x = queue.popleft()

        for i in range(4):
            ey = y + dy[i]
            ex = x + dx[i]

            if 0<=ey<N and 0<=ex<N:
                if chk[ey][ex] == 0 and chart[ey][ex] == character:
                    chk[ey][ex] = 1
                    queue.append((ey, ex))

def bfsRG(i, j):
    queue = deque()
    queue.append((i, j))
    character = chart[i][j]
    dy = [1, 0, -1, 0]
    dx = [0, 1, 0, -1]

    while (queue):
        y, x = queue.popleft()

        for i in range(4):
            ey = y + dy[i]
            ex = x + dx[i]

            if 0 <= ey < N and 0 <= ex < N:
                if chkRG[ey][ex] == 0:
                    if chart[ey][ex] == character or (chart[ey][ex] == 'R' and character == 'G') or (chart[ey][ex] == 'G' and character == 'R'):
                        chkRG[ey][ex] = 1
                        queue.append((ey, ex))



for i in range(N):
    for j in range(N):
        if (chk[i][j] == 0):
            bfs(i, j)
            region += 1

for i in range(N):
    for j in range(N):
        if (chkRG[i][j] == 0):
            bfsRG(i, j)
            regionRG += 1


print(region, regionRG)
