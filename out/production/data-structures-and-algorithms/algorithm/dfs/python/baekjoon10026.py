"""
백준 10026번

1. 아이디어
- DFS를 돌면서 구역 수를 구한다
- 적록색약일 경우 따로 DFS를 하나 더 만든다

2. 시간복잡도
- O(V+E) = N^2 + 4N^2 = 5N^2 = 50000

3. 자료구조
- mab = int[][]
- chk = bool[][]
- count = int
"""

import sys
import copy

sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = int(input())
mab = [input().strip() for _ in range(N)]
chk = [[False]*N for _ in range(N)]
chk_rg = copy.deepcopy(chk)
count = 0
count_rg = 0

dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
def dfs(y, x):

    s = mab[y][x]

    for i in range(4):
        py = y + dy[i]
        px = x + dx[i]
        if 0<=py<N and 0<=px<N:
            if chk[py][px] == False and mab[py][px] == s:
                chk[py][px] = True
                dfs(py, px)

def dfs_rg(y, x):
    s = mab[y][x]

    for i in range(4):
        py = y + dy[i]
        px = x + dx[i]
        if 0 <= py < N and 0 <= px < N:
            if chk_rg[py][px] == False:
                if mab[py][px] == s or (s == 'R' and mab[py][px] == 'G') or (s == 'G' and mab[py][px] == 'R'):
                    chk_rg[py][px] = True
                    dfs_rg(py, px)


for j in range(N):
    for i in range(N):
        if chk[j][i] == False:
            chk[j][i] = True
            dfs(j, i)
            count += 1

print(count, end=" ")

for j in range(N):
    for i in range(N):
        if chk_rg[j][i] == False:
            chk_rg[j][i] = True
            dfs_rg(j, i)
            count_rg += 1

print(count_rg)