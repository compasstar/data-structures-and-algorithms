"""
백준 16236 아기상어

1. 아이디어
- 자신의 크기보다 작고 가장 가까운 물고기를 찾는다
- 이동은 BFS를 통해 가장 짧은 것으로 구한다 (우선 순위는 위, 왼쪽)
- 먹고 다시 반복한다
- 더이상 먹을 수 있는 물고기가 없다면 종료. 시간 계산

2. 시간복잡도
- O(N*N*N*N) = 40 ** 4 = 2560000 < 2억

3. 자료구조
- 공간 space = int[][]
- 아기상어의 크기 big = int
    - 크기 증가 스택 stack = int
- 시간 time = int

- 함수 def bfs(y, x)
"""
import sys
from collections import deque

input = sys.stdin.readline


### 자료구조
N = int(input())
space = [list(map(int, input().split())) for _ in range(N)]
time = 0
big = 2
stack = 0

# 상어 처음 위치
for j in range(N):
    for i in range(N):
        if space[j][i] == 9:
            sy = j
            sx = i

dy = [-1, 0, 0, 1]
dx = [0, -1, 1, 0]

### bfs 함수
def bfs(y, x):
    global distance, sw, sy, sx
    queue = deque()
    queue.append((y, x))

    while queue:
        py, px = queue.popleft()
        if space[py][px] != 0 and space[py][px] < big:
            sw = True
            if chk[py][px] -1 < distance:
                distance = chk[py][px] - 1
                sy = py
                sx = px
            elif chk[py][px] -1 == distance:
                if py < sy:
                    sy = py
                    sx = px
                elif py == sy and px < sx:
                    sy = py
                    sx = px


        for d in range(4):
            ny = py + dy[d]
            nx = px + dx[d]
            if 0<=ny<N and 0<=nx<N:
                if chk[ny][nx] == 0:
                    if space[ny][nx] <= big:
                        chk[ny][nx] = chk[py][px]+1
                        queue.append((ny, nx))


# 상어 위치도 0으로 바꾸어 준다
space[sy][sx] = 0

### 실행
while True:
    sw = False
    chk = [[0]*N for _ in range(N)]
    chk[sy][sx] = 1
    distance = 999

    bfs(sy, sx)
    space[sy][sx] = 0
    if sw == False:
        break
    time += distance
    stack += 1
    if stack == big:
        big += 1
        stack = 0


print(time)




