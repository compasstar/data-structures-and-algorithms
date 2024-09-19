"""
백준 1504번 특정한 최단 경로

1. 아이디어
- 두 정점을 통과하는 최단 거리
- 한 번 이동한 정점과 간선 다시 이용 가능
- 다익스트라를 3번 하면 된다
    - 1번정점(출발) -> 경유지1 -> 경유지2 -> N번정점(도착)

2. 시간복잡도
- ElogV * 3 = 2e5 log800 * 3 = 18e5loge5

3. 자료구조
- edges
- dist
- heap


"""
import sys
import heapq
input = sys.stdin.readline
INF = sys.maxsize


def dijkstra(st):
    dist = [INF] * (N + 1)
    dist[st] = 0

    heap = [[0, st]]
    while heap:
        w, node = heapq.heappop(heap)
        if dist[node] != w:
            continue

        for nw, next_node in edges[node]:
            if dist[next_node] > nw + w:
                dist[next_node] = nw + w
                heapq.heappush(heap, [dist[next_node], next_node])

    return dist


N, E = map(int, input().split())
edges = [[] for _ in range(N+1)]
for _ in range(E):
    a, b, c = map(int, input().split())
    edges[a].append([c, b])
    edges[b].append([c, a])
v1, v2 = map(int, input().split())

distance = 0
distance2 = 0

# 1번 -> v1 -> v2 -> N번
dist = dijkstra(1)
distance += dist[v1]

dist = dijkstra(v1)
distance += dist[v2]

dist = dijkstra(v2)
distance += dist[N]

# 1번 -> v2 -> v1 -> N번
dist = dijkstra(1)
distance2 += dist[v2]

dist = dijkstra(v2)
distance2 += dist[v1]

dist = dijkstra(v1)
distance2 += dist[N]

answer = min(distance, distance2)
if answer >= INF:
    print(-1)
else:
    print(answer)