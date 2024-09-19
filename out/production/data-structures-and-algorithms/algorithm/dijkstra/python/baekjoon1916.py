"""
백준 1916번 최소비용 구하기

1. 아이디어
- 다익스트라

2. 시간복잡도
- ElogV
- e5 * loge3

3. 자료구조
- edges
- dist
- heap

"""
import sys
import heapq
input = sys.stdin.readline
INF = sys.maxsize

N = int(input()) # node
M = int(input()) # edge
edges = [[] for _ in range(N+1)]
for i in range(M):
    a, b, w = map(int, input().split())
    edges[a].append([w, b])

st, en = map(int, input().split())

dist = [INF] * (N+1)
dist[st] = 0

heap = [[0, st]]

while heap:
    w, node = heapq.heappop(heap)
    if w != dist[node]:
        continue

    for nw, next_node in edges[node]:
        if dist[next_node] > (nw + w):
            dist[next_node] = (nw + w)
            heapq.heappush(heap, [dist[next_node], next_node])

print(dist[en])