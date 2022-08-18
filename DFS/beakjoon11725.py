"""
백준 11725번 트리의 부모 찾기

1. 아이디어
- 우선 그래프 형태로 그린다 (1번 노드는 루트이다)
- 1번 노드부터 시작하여 dfs로 내려가서 해당 노드를 찾고 부모 노드를 출력한다

2. 시간복잡도
- O(V+E) = 4e5

3. 자료구조
- graph = int[]
- def dfs()
"""
import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

N = int(input())
graph = [[] for _ in range(N+1)]
for _ in range(N-1):
  a, b = map(int, input().split())
  graph[a].append(b)
  graph[b].append(a)

parents = [0] * (N+1)

def dfs(node):

  for n in graph[node]:
    if parents[n] == 0:
      parents[n] = node
      dfs(n)

dfs(1)

for i in range(2, N+1):
  print(parents[i])
