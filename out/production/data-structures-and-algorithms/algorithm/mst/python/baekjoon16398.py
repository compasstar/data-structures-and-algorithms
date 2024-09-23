"""
백준 16398번 행성 연결

1. 아이디어
- 모든 간선간의 비용을 측정해야 하므로 Prim 알고리즘이 적당하다
- 그래프를 만들고 Prim 알고리즘을 이용하여 최소 관리비용을 출력하자

2. 시간복잡도
- 그래프 만들기 -> N*N
- ElogE -> e6loge6

3. 자료구조
- Prim: edges, chk, heap, rs


"""
import sys, heapq
input = sys.stdin.readline

N = int(input())
edges = [[] for _ in range(N+1)]
for i in range(1, N+1):
    temp = list(map(int, input().split()))
    for j in range(1, N+1):
        edges[i].append([temp[j-1], j])

chk = [False] * (N+1)
heap = [[0, 1]]
rs = 0

while heap:
    w, node = heapq.heappop(heap)
    if chk[node] == False:
        chk[node] = True
        rs += w

        for e in edges[node]:
            if chk[e[1]] == False:
                heapq.heappush(heap, e)

print(rs)

