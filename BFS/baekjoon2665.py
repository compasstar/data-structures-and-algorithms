"""
백준 2665번 미로만들기

1. 아이디어
- bfs 를 이용하여 끝방까지의 거리를 구한다
- 만일 흰방이라면 appendleft를 이용하여
    큐에서 먼저 나올 수 있도록 해주게 한다
- 검은방이라면 append를 하여 큐에서 늦게 나오게 한다

2. 시간복잡도
- O(V+E) = N^2

3. 자료구조
- miro
- chk
- bfs
- cnt

"""
import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
miro = [list(map(int, input().strip())) for _ in range(n)]
chk = [[-1] * n for _ in range(n)]
cnt = 0

dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
def bfs(y, x):
    q = deque()
    q.append((y, x))
    chk[y][x] = 0

    while q:
        py, px = q.popleft()

        for d in range(4):
            ey = py + dy[d]
            ex = px + dx[d]
            if 0<=ey<n and 0<=ex<n:
                if chk[ey][ex] == -1:
                    if miro[ey][ex] == 1:
                        chk[ey][ex] = chk[py][px]
                        q.appendleft((ey, ex))
                    else:
                        chk[ey][ex] = chk[py][px] + 1
                        q.append((ey, ex))

bfs(0, 0)
print(chk[n-1][n-1])


