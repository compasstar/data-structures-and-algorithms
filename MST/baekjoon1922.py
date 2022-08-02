"""
백준 1922번 네트워크 연결

1. 아이디어

2. 시간복잡도

3. 자료구조

"""
import sys
import heapq
input = sys.stdin.readline

N = int(input()) # vertex
M = int(input()) # edge

edges = [[] for _ in range(N+1)]
chk = [False] * (N+1)

for _ in range(M):
    a, b, c = map(int, input().split())
    edges[a].append([c, b])
    edges[b].append([c, a])

rs = 0
heap = [[0, 1]]

while heap:

    w, next_node = heapq.heappop(heap)

    if chk[next_node] == False:
        chk[next_node] = True
        rs += w

        for node in edges[next_node]:
            if chk[node[1]] == False:
                heapq.heappush(heap, node)

print(rs)

