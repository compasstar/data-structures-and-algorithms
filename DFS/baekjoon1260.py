"""
백준 1260

1. 아이디어
- 그래프를 만든다
- DFS 돌면서 출력

2. 시간복잡도
- DFS: O(V+E)
- V, E: N = 1000, M = 10000
- V + E = 11000 -> 가능

3. 자료구조
- 그래프: int[][]
- 방문여부: bool[]
"""

import sys
from collections import deque

input = sys.stdin.readline

N, M, V = map(int, input().split())
dfs_list = []
bfs_list = []

graph = [[] for _ in range(N+1)]
chk = [False] * (N+1)
chk_bfs = [False] * (N+1)



for _ in range(M):
    first, second = map(int, input().split())
    graph[first].append(second)
    graph[second].append(first)

for i in range(1, N+1):
    graph[i].sort()

def dfs(start):
    dfs_list.append(start)
    chk[start] = True

    for i in graph[start]:
        if chk[i] == False:
            dfs(i)

def bfs(start):
    queue = deque()
    queue.append(start)
    chk_bfs[start] = True

    while(queue):
        v = queue.popleft()
        bfs_list.append(v)

        for i in graph[v]:
            if chk_bfs[i] == False:
                queue.append(i)
                chk_bfs[i] = True


dfs(V)
bfs(V)

print(*dfs_list)
print(*bfs_list)
