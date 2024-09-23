"""
백준 11724번

1. 아이디어
- DFS 로 돌면서 연결요소 개수 센다
- 그래프 for 문 돈다

2. 시간복잡도
- O(V+E) = N + M <= 1,000,000

3. 자료구조
- graph = int[][]
- chk = bool[][]
- 연결요소 개수 number = int

"""
import sys

sys.setrecursionlimit(10**6)

input = sys.stdin.readline

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]
chk = [False] * (N+1)
number = 0

for i in range(M):
    first, second = map(int, input().split())
    graph[first].append(second)
    graph[second].append(first)


def dfs(start):
    for i in graph[start]:
        if chk[i] == False:
            chk[i] = True
            dfs(i)

for i in range(1, N+1):
    if chk[i] == False:
        chk[i] = True
        dfs(i)
        number += 1

print(number)