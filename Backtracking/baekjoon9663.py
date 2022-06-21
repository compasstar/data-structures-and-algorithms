"""
백준 9663번

1. 아이디어
- 체스판 크기가 N*N 이고 놓는 체스말 수가 N이다
- 행으로 봐도 열로 봐도 각 줄에 하나 씩만 체스 말을 놓을 수 있다
- 각 줄마다 체스 말을 놓아보면서 경우의 수를 센다

- 퀸의 경로라면 체스판에 +1을 한다
- 백트래킹을 하며 돌아갈 때는 -1을 한다
- 0일 경우에만 말을 놓을 수 있다
- 말을 못 놓는 경우가 중복이 생기기에 위의 과정이 필요하다

2. 시간복잡도

3. 자료구조
- 체스판 chess = int[][]
"""

import sys
input = sys.stdin.readline

N = int(input())
chess = [[0]*N for _ in range(N)]
answer = 0

def recur(num):
    global answer, chess

    if num == N:
        answer += 1
        return

    for x in range(N):
        if chess[num][x] == 0:

            for y in range(num+1, N):
                chess[y][x] += 1
                if 0<=x+y-num<N:
                    chess[y][x+y-num] += 1
                if 0<=x-(y-num)<N:
                    chess[y][x-(y-num)] += 1

            recur(num + 1)

            for y in range(num+1, N):
                chess[y][x] -= 1
                if 0<=x+y-num<N:
                    chess[y][x+y-num] -= 1
                if 0<=x-(y-num)<N:
                    chess[y][x-(y-num)] -= 1


recur(0)
print(answer)
