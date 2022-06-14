"""
백준 1012번

1. 아이디어
- DFS로 지도를 돌면서 영토를 센다

2. 시간복잡도
- O(V+E)
- V = N*M = 2500
- E = 4V = 10000
- V + E = 12500

3. 자료구조
- 지도 map = int[][]
- 방문 chk = bool[][]
- 영토수 region = int
"""

import sys
sys.setrecursionlimit(10 ** 6)

input = sys.stdin.readline

answer = []

T = int(input())
for _ in range(T):

    dy = [1, 0, -1, 0]
    dx = [0, 1, 0, -1]
    def dfs(y, x):

        for i in range(4):
            py = y + dy[i]
            px = x + dx[i]

            if 0<=py<N and 0<=px<M:
                if chk[py][px] == False and mab[py][px] == 1:
                    chk[py][px] = True
                    dfs(py, px)




    M, N, K = map(int, input().split())

    mab = [[0] * M for _ in range(N)]
    chk = [[False] * M for _ in range(N)]
    region = 0


    for _ in range(K):
        x, y = map(int, input().split())
        mab[y][x] = 1


    for j in range(N):
        for i in range(M):
            if chk[j][i] == False and mab[j][i] == 1:
                chk[j][i] = True
                dfs(j, i)
                region += 1

    answer.append(region)


for i in answer:
    print(i)



