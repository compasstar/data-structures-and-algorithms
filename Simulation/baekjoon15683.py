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
cctv = []
for j in range(N):
    for i in range(M):
        if graph[j][i] == 1 or graph[j][i] == 2 or graph[j][i] == 3 or graph[j][i] == 4 or graph[j][i] == 5:
            cctv.append((j, i))
min_region = 64


dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
def watch(y, x, cctv_num, dir):
    if cctv_num == 1:
        ny = y
        nx = x
        while True:
            ny += dy[dir]
            nx += dx[dir]
            if ny<0 or ny>=N or nx<0 or M<=nx or graph[ny][nx] == 6:
                break

            if graph[ny][nx] == 0:
                graph[ny][nx] = 7

    elif cctv_num == 2:
        ny1, nx1 = y, x
        ny2, nx2 = y, x
        while True:
            ny1 += dy[dir]
            nx1 += dx[dir]

            if ny1<0 or ny1>=N or nx1<0 or M<=nx1 or graph[ny1][nx1] == 6:
                break

            if graph[ny1][nx1] == 0:
                graph[ny1][nx1] = 7

        while True:
            ny2 += dy[dir+2]
            nx2 += dx[dir+2]

            if ny2 < 0 or ny2 >= N or nx2 < 0 or M <= nx2 or graph[ny2][nx2] == 6:
                break

            if graph[ny2][nx2] == 0:
                graph[ny2][nx2] = 7


    elif cctv_num == 3:

        ny1, nx1 = y, x
        ny2, nx2 = y, x
        while True:
            ny1 += dy[dir]
            nx1 += dx[dir]

            if ny1 < 0 or ny1 >= N or nx1 < 0 or M <= nx1 or graph[ny1][nx1] == 6:
                break

            if graph[ny1][nx1] == 0:
                graph[ny1][nx1] = 7

        while True:
            ny2 += dy[(dir + 1)%4]
            nx2 += dx[(dir + 1)%4]

            if ny2 < 0 or ny2 >= N or nx2 < 0 or M <= nx2 or graph[ny2][nx2] == 6:
                break

            if graph[ny2][nx2] == 0:
                graph[ny2][nx2] = 7



    elif cctv_num == 4:
        ny1, nx1 = y, x
        ny2, nx2 = y, x
        ny3, nx3 = y, x

        while True:
            ny1 += dy[dir%4]
            nx1 += dx[dir%4]

            if ny1<0 or ny1>=N or nx1<0 or M<=nx1 or graph[ny1][nx1] == 6:
                break

            if graph[ny1][nx1] == 0:
                graph[ny1][nx1] = 7

        while True:
            ny2 += dy[(dir+1)%4]
            nx2 += dx[(dir+1)%4]

            if ny2<0 or ny2>=N or nx2<0 or M<=nx2 or graph[ny2][nx2] == 6:
                break

            if graph[ny2][nx2] == 0:
                graph[ny2][nx2] = 7

        while True:
            ny3 += dy[(dir+2)%4]
            nx3 += dx[(dir+2)%4]

            if ny3<0 or ny3>=N or nx3<0 or M<=nx3 or graph[ny3][nx3] == 6:
                break

            if graph[ny3][nx3] == 0:
                graph[ny3][nx3] = 7

    else:
        ny1, nx1 = y, x
        ny2, nx2 = y, x
        ny3, nx3 = y, x
        ny4, nx4 = y, x

        while True:
            ny1 += dy[dir % 4]
            nx1 += dx[dir % 4]

            if ny1 < 0 or ny1 >= N or nx1 < 0 or M <= nx1 or graph[ny1][nx1] == 6:
                break

            if graph[ny1][nx1] == 0:
                graph[ny1][nx1] = 7

        while True:
            ny2 += dy[(dir + 1) % 4]
            nx2 += dx[(dir + 1) % 4]

            if ny2 < 0 or ny2 >= N or nx2 < 0 or M <= nx2 or graph[ny2][nx2] == 6:
                break

            if graph[ny2][nx2] == 0:
                graph[ny2][nx2] = 7

        while True:
            ny3 += dy[(dir + 2) % 4]
            nx3 += dx[(dir + 2) % 4]

            if ny3 < 0 or ny3 >= N or nx3 < 0 or M <= nx3 or graph[ny3][nx3] == 6:
                break

            if graph[ny3][nx3] == 0:
                graph[ny3][nx3] = 7

        while True:
            ny4 += dy[(dir + 3) % 4]
            nx4 += dx[(dir + 3) % 4]

            if ny4 < 0 or ny4 >= N or nx4 < 0 or M <= nx4 or graph[ny4][nx4] == 6:
                break

            if graph[ny4][nx4] == 0:
                graph[ny4][nx4] = 7






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
    if cctv_num == 1:
        for d in range(4):
            graph_copy = copy.deepcopy(graph)
            watch(y, x, 1, d)
            recur(cnt + 1)
            graph = graph_copy
    elif cctv_num == 2:
        for d in range(2):
            graph_copy = copy.deepcopy(graph)
            watch(y, x, 2, d)
            recur(cnt + 1)
            graph = graph_copy
    elif cctv_num == 3:
        for d in range(4):
            graph_copy = copy.deepcopy(graph)
            watch(y, x, 3, d)
            recur(cnt + 1)
            graph = graph_copy
    elif cctv_num == 4:
        for d in range(4):
            graph_copy = copy.deepcopy(graph)
            watch(y, x, 4, d)
            recur(cnt + 1)
            graph = graph_copy
    else:
        graph_copy = copy.deepcopy(graph)
        watch(y, x, 5, 0)
        recur(cnt + 1)
        graph = graph_copy

recur(0)
print(min_region)