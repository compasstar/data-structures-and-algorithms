"""
1. 아이디어
- 벽 3개를 모든 경우의 수만큼 세워본다
- 벽을 세우고 BFS를 통해 퍼뜨린다
- 남아있는 안전공간의 개수를 센다

2. 시간복잡도
- 벽 3개를 세우는 경우의 수: NxM C 3 = (NxM)(NxM-1)(NxM-2)/6
- BFS를 통해 퍼뜨리는 시간: O(V+E) = NxMx5

-> O((NxM)^4) = 64^4 = 약 2천만 <= 1억

3. 자료구조
- 변수
- chart
- answer

- 함수
- makeWall()
- bfs()
    -> copymap
- cntSafe()
"""

import sys
import copy
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())
chart = [list(map(int, input().split())) for _ in range(N)]
safeList = []


def makeWall(cntWall, startColumn):
    if (cntWall == 3):
        bfs()
        return

    for j in range(startColumn, N):
        for i in range(M):
            if chart[j][i] == 0:
                chart[j][i] = 1
                makeWall(cntWall + 1, j)
                chart[j][i] = 0




def bfs():
    copyMap = copy.deepcopy(chart)
    queue = deque()
    dy = [1, 0, -1, 0]
    dx = [0, 1, 0, -1]

    for j in range(N):
        for i in range(M):
            if copyMap[j][i] == 2:
                queue.append((j, i))

    while queue:
        py, px = queue.popleft()

        for i in range(4):
            ey = py + dy[i]
            ex = px + dx[i]

            if 0<=ey<N and 0<=ex<M:
                if copyMap[ey][ex] == 0:
                    copyMap[ey][ex] = 2
                    queue.append((ey,ex))

    cntSafe(copyMap)





def cntSafe(copyMap):

    safeCnt = 0
    for j in range(N):
        for i in range(M):
            if copyMap[j][i] == 0:
                safeCnt += 1

    safeList.append(safeCnt)


makeWall(0, 0)
answer = max(safeList)
print(answer)
