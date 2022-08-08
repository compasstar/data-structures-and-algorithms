"""
백준 18352번 특정 거리의 도시 찾기

1. 아이디어
- 다익스트라로 최단거리를 찾아서 출력한다

2. 시간복잡도
- ElogV = M * log N = e6loge5

3. 자료구조
- edges
- dist
- heap

"""
import sys
import heapq
input = sys.stdin.readline
INF = sys.maxsize

N, M, K, X = map(int, input().split())

edges = [[] for _ in range(N+1)]
for _ in range(M):
    A, B = map(int, input().split())
    edges[A].append([1, B])

dist = [INF] * (N+1)
dist[X] = 0

heap = [[0, X]]
while heap:
    w, node = heapq.heappop(heap)
    if w != dist[node]:
        continue

    for nw, next_node in edges[node]:
        if dist[next_node] > nw + w:
            dist[next_node] = nw + w
            heapq.heappush(heap, [dist[next_node], next_node])

city = []
for i in range(1, N+1):
    if dist[i] == K:
        city.append(i)

if len(city) == 0:
    print(-1)
else:
    city.sort()
    for c in city:
        print(c)
