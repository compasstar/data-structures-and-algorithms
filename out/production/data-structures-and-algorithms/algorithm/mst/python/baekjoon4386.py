"""
백준 4386번 별자리 만들기

1. 아이디어
- 별을 전부 이어서 시작점, 끝점, 거리로 나타낸다
- MST 실행

2. 시간복잡도
- 별을 시작점, 끝점, 거리로 나타내기 -> N^2 -> 10000
- MST -> ElgE -> e^4lge^4

3. 자료구조
- heap
- chk
- edge
- rs

"""
import sys, heapq
input = sys.stdin.readline

n = int(input())

star = [list(map(float, input().split())) for _ in range(n)]
edge = [[] for _ in range(n)]

for i in range(n):
    for j in range(i):
        distance = ((star[i][0] - star[j][0])**2 + (star[i][1] - star[j][1])**2)**0.5
        edge[i].append([distance, j])
        edge[j].append([distance, i])

chk = [False] * n
heap = [[0, 0]]
rs = 0


while heap:

    w, node = heapq.heappop(heap)
    if chk[node] == False:
        chk[node] = True
        rs += w

        for e in edge[node]:
            if chk[e[1]] == False:
                heapq.heappush(heap, e)

print(rs)