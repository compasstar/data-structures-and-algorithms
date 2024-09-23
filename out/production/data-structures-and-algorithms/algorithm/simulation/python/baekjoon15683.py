"""
백준 15683 감시

1. 아이디어
- 백트래킹으로 각 CCTV의 방향을 전부 구하고, 0이 최소가 되도록 구한다

2. 시간복잡도
- O(4**N) = 256*256 = 65536

3. 자료구조
- 사무실 graph = [][]
- cctv좌표 cctv = [(y, x)]
- 사각지대 최소 크기: min_region
- 백트래킹 def recur(num)
- 해당 방향으로 CCTV 감시구역 설정 def watch(dir)
"""

import sys
import copy
input = sys.stdin.readline

N, M = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]
cctv_kind = [[], [[0], [1], [2], [3]], [[0, 2], [1, 3]], [[0, 1], [1, 2], [2, 3], [3, 0]], [[0, 1, 2], [1, 2, 3], [2, 3, 0], [3, 0, 1]], [[0, 1, 2, 3]]]
cctv = []
for j in range(N):
    for i in range(M):
        if graph[j][i] == 1 or graph[j][i] == 2 or graph[j][i] == 3 or graph[j][i] == 4 or graph[j][i] == 5:
            cctv.append((j, i))
min_region = 64


dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
def watch(y, x, cctv_list):

    for i in cctv_list:
        ny = y
        nx = x
        while True:
            ny += dy[i]
            nx += dx[i]
            if ny<0 or ny>=N or nx<0 or M<=nx or graph[ny][nx] == 6:
                break

            if graph[ny][nx] == 0:
                graph[ny][nx] = 7



def recur(cnt):
    global min_region, graph

    if cnt == len(cctv):
        region = 0
        for j in range(N):
            for i in range(M):
                if graph[j][i] == 0:
                    region += 1
        min_region = min(min_region, region)
        return

    y, x = cctv[cnt]
    cctv_num = graph[y][x]
    for i in cctv_kind[cctv_num]:
        graph_copy = copy.deepcopy(graph)
        watch(y, x, i)
        recur(cnt + 1)
        graph = graph_copy



recur(0)
print(min_region)
