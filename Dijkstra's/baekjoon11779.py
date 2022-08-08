"""
백준 11779번 최소비용 구하기 2

1. 아이디어
- 다익스트라로 A에서 각 노드의 거리를 계산하고, B에 해당하는 거리를 출력

2. 시간복잡도
- ElogV = e5 loge3

3. 자료구조
- edges
- dist
- heap

"""
import sys
import heapq
input = sys.stdin.readline
INF = sys.maxsize

n = int(input()) # node
m = int(input()) # edge
edges = [[] for _ in range(n+1)]
for _ in range(m):
    st, en, w = map(int, input().split())
    edges[st].append([w, en])

start, end = map(int, input().split())
dist = [INF] * (n+1)
dist[start] = 0

paths = [ [start] for _ in range(n+1) ]

heap = [[0, start, [start]]]
while heap:
    w, node, path = heapq.heappop(heap)
    if dist[node] != w:
        continue

    for nw, next_node in edges[node]:
        if dist[next_node] > nw + w:
            dist[next_node] = nw + w
            temp_path = path.copy()
            temp_path.append(next_node)
            paths[next_node] = temp_path
            heapq.heappush(heap, [dist[next_node], next_node, temp_path])

print(dist[end])
print(len(paths[end]))
print(" ".join(map(str, paths[end])))
