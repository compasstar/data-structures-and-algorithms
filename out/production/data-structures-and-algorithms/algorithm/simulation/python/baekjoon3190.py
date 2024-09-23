"""
백준 3190 뱀

1. 아이디어
- 보드에서 뱀을 규칙에 따라 이동시킨다
- 시간을 진행시키며 몸을 늘리고, 사과가 아니면 꼬리 부분을 없앤다
- 벽 또는 자기자신의 몸과 부딪히면 게임 종료

2. 시간복잡도
- O(N*N) = 10000

3. 자료구조
- 보드 board = int[][]
    - 보드 위에 비어있다면 0, 뱀이 있다면 1, 사과가 있다면 2
- 뱀 snake = int[][y, x]  --> 스택 구조로 인덱스 앞쪽이 꼬리, 뒷쪽이 머리
- 방향 변환 정보 info  = [[X초, C방향], [ ], ...]

- 시간 time = int
"""
import sys
input = sys.stdin.readline


# 자료구조
N = int(input())
board = [[0]*N for _ in range(N)]
snake = []
snake.append([0, 0])

board[0][0] = 1

K = int(input())
for _ in range(K):
    ky, kx = map(int, input().split())
    ky -= 1
    kx -= 1
    board[ky][kx] = 2

L = int(input())
info = []
for _ in range(L):
    i = list(input().split())
    info.append(i)

time = 1

dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
d = 1 # 처음에는 뱀이 오른쪽을 향한다

while True:

    # 머리 위치
    y = snake[-1][0]
    x = snake[-1][1]
    # 꼬리 위치
    zy = snake[0][0]
    zx = snake[0][1]
    # 늘린 머리 위치
    ny = y + dy[d]
    nx = x + dx[d]

    # 보드의 바깥이나 뱀을 만나면 종료한다
    if ny<0 or N<=ny or nx<0 or N<=nx or board[ny][nx] == 1:
        break

    if board[ny][nx] == 0:
        board[zy][zx] = 0
        snake.pop(0)

    board[ny][nx] = 1
    snake.append([ny, nx])
    y = ny
    x = nx


    if len(info) != 0:
        if time == int(info[0][0]):
            if info[0][1] == 'L':
                d = (d + 1) % 4
            else:
                d = (d + 3) % 4
            info.pop(0)

    time += 1



print(time)

