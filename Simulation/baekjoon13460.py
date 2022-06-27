"""
백준 13460번 구슬탈출2

1. 아이디어
- BFS를 통해 두 공의 위치들을 탐색한다
- 10번을 초과한다면 -1을 출력한다

2. 시간복잡도
- O(V+E) = O(N*M)

3. 자료구조
- 보드: board = int[N][M]
- 방문: chk = [(ry, rx, by, bx), ...]
- 함수: def bfs()
"""

import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())
board = [list(input().strip()) for _ in range(N)]
chk = []

for j in range(N):
    for i in range(M):
        if board[j][i] == 'R':
            ry, rx = j, i
        elif board[j][i] == 'B':
            by, bx = j, i


dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]

def bfs(ry, rx, by, bx):
    queue = deque()
    queue.append((ry, rx, by, bx))
    chk.append((ry, rx, by, bx))
    cnt = 1

    while queue:

        if cnt > 10:
            return -1

        for i in range(len(queue)):

            pry, prx, pby, pbx = queue.popleft()

            for d in range(4):
                nry, nrx = pry, prx
                nby, nbx = pby, pbx

                while True:
                    nry += dy[d]
                    nrx += dx[d]

                    if board[nry][nrx] == 'O':
                        break
                    elif board[nry][nrx] == '#':
                        nry -= dy[d]
                        nrx -= dx[d]
                        break

                while True:
                    nby += dy[d]
                    nbx += dx[d]

                    if board[nby][nbx] == 'O':
                        break
                    elif board[nby][nbx] == '#':
                        nby -= dy[d]
                        nbx -= dx[d]
                        break

                if board[nby][nbx] == 'O':
                    continue
                if board[nry][nrx] == 'O':
                    return cnt

                if (nry == nby and nrx == nbx):
                    if (abs(nry - pry) + abs(nrx - prx)) > (abs(nby - pby) + abs(nbx - pbx)):
                        nry -= dy[d]
                        nrx -= dx[d]
                    else:
                        nby -= dy[d]
                        nbx -= dx[d]


                if (nry, nrx, nby, nbx) not in chk:
                    chk.append((nry, nrx, nby, nbx))
                    queue.append((nry, nrx, nby, nbx))

        cnt += 1

    return -1

cnt = bfs(ry, rx, by, bx)
print(cnt)