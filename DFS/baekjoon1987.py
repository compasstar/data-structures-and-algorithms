"""
백준 1987번

1. 아이디어
- DFS로 여태까지랑 다른 걸로 돈다

2. 시간복잡도
- O(V+E) = 20*20 + 4*20*20

3. 자료구조
- 지도 region = char[][]
- 방문여부 chk = bool[][]


* 이유는 모르겠지만 `sys.stdin.readline`을 지우고, PyPy3로 실행해야만 통과할 수 있다
"""


import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

R, C = map(int, input().split())
region = [list(map(lambda x: ord(x)-ord('A'), input().strip())) for _ in range(R)]
duplication = [False]*26

dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
answer = 1
def dfs(y, x, num):
    global answer
    answer = max(answer, num)
    if answer == 26:
        return
    for i in range(4):
        py = y + dy[i]
        px = x + dx[i]
        if 0<=py<R and 0<=px<C:
            if duplication[region[py][px]] == False:
                duplication[region[py][px]] = True
                dfs(py, px, num+1)
                duplication[region[py][px]] = False

duplication[region[0][0]] = True
dfs(0, 0, 1)
print(answer)


