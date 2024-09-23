"""
백준 4963번

1. 아이디어
- DFS로 돌면서 섬의 개수를 구한다

2. 시간복잡도
- O(V+E) = wh + 8wh = 9wh = 9*2500

3. 자료구조
- 지도 mab = int[][]
- 방문 chk = bool[][]
- 섬의 개수 count = int
"""

import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline



while(True):

    w, h = map(int, input().split())

    if w == 0 and h == 0:
        break

    mab = [list(map(int, input().split())) for _ in range(h)]
    chk = [[False]*w for _ in range(h)]
    count = 0

    dy = [1, 0, -1, 0, 1, -1, -1, 1]
    dx = [0, 1, 0 ,-1, 1, 1, -1, -1]
    def dfs(y, x):
        for i in range(8):
            py = y + dy[i]
            px = x + dx[i]

            if 0<=py<h and 0<=px<w:
                if chk[py][px] == False and mab[py][px] == 1:
                    chk[py][px] = True
                    dfs(py, px)

    for j in range(h):
        for i in range(w):
            if chk[j][i] == False and mab[j][i] == 1:
                chk[j][i] = True
                dfs(j, i)
                count += 1

    print(count)