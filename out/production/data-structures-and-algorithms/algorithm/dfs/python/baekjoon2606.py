"""
백준 2606

1. 아이디어
- DFS를 통해 돌면서 센다

2. 시간복잡도
- O(V+E)
- V = 100, E = V!

3. 자료구조
- graph = int[][]
- chk = bool[]
"""

import sys

input = sys.stdin.readline

N = int(input())
M = int(input())
graph = [[] for _ in range(N+1)]
chk = [False] * (N+1)
answer = 0

for _ in range(M):
    first, second = map(int, input().split())
    graph[first].append(second)
    graph[second].append(first)

def dfs(start):
    global answer
    answer += 1

    for i in graph[start]:
        if chk[i] == False:
            chk[i] = True
            dfs(i)

chk[1] = True
dfs(1)
print(answer-1)