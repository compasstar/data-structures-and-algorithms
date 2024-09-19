"""
1. 아이디어
BFS 로 돌면서 땅의 개수 구한다


2. 시간복잡도
O(V+E)


3. 자료구조
seeMap
chk
cnt 섬의 개수
BFS: queue
"""

import sys
from collections import deque

input = sys.stdin.readline

while(True):

    w, h = map(int, input().split())

    if w==0 and h==0:
        break

    seeMap = [list(map(int, input().split())) for _ in range(h)]
    chk = [[False]*w for _ in range(h)]
    count = 0


    def BFS(coordinate):

        dy = [1, 0, -1, 0, 1, -1, -1, 1]
        dx = [0, 1, 0, -1, 1, 1, -1, -1]

        queue = deque()
        queue.append(coordinate)
        chk[coordinate[0]][coordinate[1]] = True

        while(queue):
            py, px = queue.popleft()


            for i in range(8):
                ey = py + dy[i]
                ex = px + dx[i]

                if (0<=ey<h) and (0<=ex<w):
                    if (chk[ey][ex] == False) and (seeMap[ey][ex] == 1):
                        queue.append((ey, ex))
                        chk[ey][ex] = True




    for j in range(h):
        for i in range(w):
            if (chk[j][i] == False) and (seeMap[j][i] == 1):
                BFS((j, i))
                count += 1


    print(count)
