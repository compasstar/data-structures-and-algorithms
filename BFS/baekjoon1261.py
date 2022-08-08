"""
백준 1261번 알고스팟

1. 아이디어
(1, 1) -> (N, M) 으로 갈 때 부서야 하는 벽의 개수
- BFS로 돌고 못찾는다면 가장 바깥의 벽을 부수고 카운트 실행
- 다시 BFS 돈다.
- 위 과정으로 N, M 에 도달할 때까지 반복

2. 시간복잡도
- (V+E)^2

3. 자료구조
- miro
- chk
- def bfs(x, y)
- cnt

"""
import sys
from collections import deque
input = sys.stdin.readline

M, N = map(int, input().split())
miro = [list(map(int, input().strip())) for _ in range(N)]

dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
cnt = 0

def bfs(y, x):
    q = deque()
    q.append((y, x))
    chk[y][x] = 0

    while q:
        py, px = q.popleft()

        for d in range(4):
            ey = py + dy[d]
            ex = px + dx[d]

            if 0<=ey<N and 0<=ex<M:
                if chk[ey][ex] == -1:
                    if miro[ey][ex] == 0:
                        chk[ey][ex] = chk[py][px]
                        q.appendleft((ey, ex))
                    else:
                        chk[ey][ex] = chk[py][px] + 1
                        q.append((ey, ex))


chk = [[-1] * M for _ in range(N)]
bfs(0, 0)
print(chk[N-1][M-1])