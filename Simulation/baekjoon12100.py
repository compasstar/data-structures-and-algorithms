"""
백준 12100번 2048 (easy)

1. 아이디어
- 백트래킹을 통해 상하좌우를 실행한다 5번 실행 후 종료. 가장 큰 블록 값 구하기
- 상하좌우 이동 함수: 이동시 같은 값이면 합쳐짐. 단, 이미 합쳐지면 아님


1. 한 줄에 있는 모든 수를 queue에 넣고, 보드에는 0으로 바꾼다
2. 큐를 pop하면서 숫자를 하나씩 보드에 넣는다
3. 규칙에 맞게 한다 (1. 숫자가 비어있으면 삽입, 2. 숫자가 같으면 합치고 다음, 3. 숫자가 다르면 다음 것에 집어넣기)

- 모든 줄에 관하여 실행

2. 시간복잡도
- O(이동함수 걸리는 시간**5)

3. 자료구조
- 보드: board = int[N][N]
- 미는함수: def sum_tile(dir)
- 백트래킹함수: def recur(num)
- 가장 값이 큰 타일: int max_tile

"""

import sys
import copy
from collections import deque

input = sys.stdin.readline

N = int(input())
board = [list(map(int, input().split())) for _ in range(N)]
max_tile = 0
queue = deque()


def move_queue(y, x):
    if board[y][x] != 0:
        queue.append(board[y][x])
        board[y][x] = 0

def merge(y, x, dy, dx):
    while queue:
        value = queue.popleft()
        if board[y][x] == 0:
            board[y][x] = value
        elif board[y][x] == value:
            board[y][x] *= 2
            y += dy
            x += dx
        else:
            y += dy
            x += dx
            board[y][x] = value


def sum_tile(dir):
    if dir == 0: # 아래로 밀기
        for i in range(N):
            for j in range(N-1, -1, -1):
                move_queue(j, i)
            merge(N-1, i, -1, 0)

    elif dir == 1: # 오른쪽으로 밀기
        for j in range(N):
            for i in range(N-1, -1, -1):
                move_queue(j, i)
            merge(j, N-1, 0, -1)

    elif dir == 2: # 위로 밀기
        for i in range(N):
            for j in range(N):
                move_queue(j, i)
            merge(0, i, 1, 0)

    else: # 왼쪽으로 밀기
        for j in range(N):
            for i in range(N):
                move_queue(j, i)
            merge(j, 0, 0, 1)


def recur(num):
    global board, max_tile
    if num == 5:
        for i in range(N):
            max_tile = max(max_tile, max(board[i]))
        return

    for dir in range(4):
        board_origin = copy.deepcopy(board)
        sum_tile(dir)
        recur(num+1)
        board = board_origin


recur(0)
print(max_tile)
