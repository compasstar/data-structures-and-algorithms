"""
1. 아이디어
chk 로 N개의 False를 만든 후, 방문 후 True로 만든다
한 번 사이클 돌면 연결요소 +1

2. 시간복잡도
O(M^2)
O(N^4) = 1,000,000,000,000

3. 자료구조
chk
vertex
queue
"""



import sys
from collections import deque

def BFS(start):
    queue = deque()
    queue.append(start)
    chk[start] = True

    while queue:
        num = queue.popleft()
        for i in graph[num]:
            if chk[i] == False:
                queue.append(i)
                chk[i] = True



input = sys.stdin.readline

N, M = map(int, input().split())

graph = [[0] for _ in range(N+1)]
chk = [False] * (N+1)
cnt = 0

for _ in range(M):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

for i in range(1, N+1):
    if chk[i] == False:
        BFS(i)
        cnt += 1

print(cnt)
