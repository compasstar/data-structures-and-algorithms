"""
백준 13549번 숨바꼭질 3

1. 아이디어
- 순간이동하는 데에는, 즉 2*X를 하는 데에는 시간이 들지 않는다!!!
- DP 로 풀어보자 점화식 세워보자
    - 만일 2의 배수라면 i/2 값과 인덱스 +-1 중 작은거
    - 만일 2의 배수가 아니라면 인덱스 +-1 중 작은 것보다 1 커진다
    - 이건 DP로 인덱스를 구할때 1부터 차례로 커지지 않으므로 아닌것 같다

- 시작점부터 목적지까지 최단거리 구하는 것이므로 다익스트라?
- 노드 대신 그냥 지도로 하면 가능?

2. 시간복잡도
- ElogV

3. 자료구조
- edges -> 필요없다 숫자 0 ~ 100000 범위가 곧 edges 임
- dist
- heap

"""
import sys
import heapq
input = sys.stdin.readline
INF = sys.maxsize

N, K = map(int, input().split())

dist = [INF] * (100001)
dist[N] = 0
heap = [[0, N]]

while heap:
    w, node = heapq.heappop(heap)
    if dist[node] != w:
        continue

    nw = 0
    next_node = node * 2
    if next_node <= 100000:
        if dist[next_node] > nw + w:
            dist[next_node] = nw + w
            heapq.heappush(heap, [dist[next_node], next_node])

    nw = 1
    next_node = node + 1
    if next_node <= 100000:
        if dist[next_node] > nw + w:
            dist[next_node] = nw + w
            heapq.heappush(heap, [dist[next_node], next_node])

    nw = 1
    next_node = node - 1
    if 0 <= next_node:
        if dist[next_node] > nw + w:
            dist[next_node] = nw + w
            heapq.heappush(heap, [dist[next_node], next_node])

print(dist[K])