"""
백준 1238번 파티

1. 아이디어
- 최단경로 구하기 -> 다익스트라
- X에 갈 때 다익스트라를 모든 N에 대하여 실행한다 -> time에 시간 저장
- X에서 돌아 올때 dist 값을 time 에 더하여 max값을 구한다


2. 시간복잡도
- N * (ElogV)
- e7 loge3

3. 자료구조
- edges
- dist
- heap
- time

"""
import sys
import heapq
input = sys.stdin.readline
INF = sys.maxsize

N, M, X = map(int, input().split())
edges = [[] for _ in range(N+1)]
for i in range(M):
    st, en, t = map(int, input().split())
    edges[st].append([t, en])

time = [0] * (N+1)

for start in range(1, N+1):
    dist = [INF] * (N+1)
    dist[start] = 0

    heap = [[0, start]]
    while heap:
        w, node = heapq.heappop(heap)
        if w != dist[node]:
            continue

        for nw, next_node in edges[node]:
            if dist[next_node] > nw + w:
                dist[next_node] = nw + w
                heapq.heappush(heap, [dist[next_node], next_node])

    time[start] = dist[X]


# 돌아올 때
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

for i in range(1, N+1):
    time[i] += dist[i]

answer = max(time)
print(answer)








