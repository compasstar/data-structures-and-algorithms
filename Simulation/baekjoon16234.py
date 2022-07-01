"""
백준 16234번 인구이동

1. 아이디어
- BFS를 통해 돌면서 인구차이가 L이상, R이하라면 체크하고, 합을 구하고 좌표를 저장해둔다
- 체크된 곳에 합을 나누어 분배한다

- 위 과정을 반복한다
- chk2를 두어 위 과정을 반복할 때, 인구이동이 벌어지지 않은 곳은 bfs를 실행시키지 않는다
- 인구이동을 하지 않은 곳에서 bfs를 시작 할 필요가 없다! (bfs를 돌다 만나면 물론 검사를 해야한다)

2. 시간복잡도
- O(V+E) * day = N*2000 = 100000 < 2억

3. 자료구조
- 지도 graph = int[N][N]
- 날짜 day = int
- chk = int[][]
- chk2 = int[][] -> 인구이동이 지난번 페이즈에 벌어졌거나 처음이라면 False 아니라면 True
"""

import sys
from collections import deque

input = sys.stdin.readline

N, L, R = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]
day = 0
chk2 = [[False]*N for _ in range(N)]

dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
def bfs(y, x):
    global population, sw
    queue = deque()
    queue.append((y, x))

    while queue:
        py, px = queue.popleft()
        for d in range(4):
            ny = py + dy[d]
            nx = px + dx[d]
            if 0<=ny<N and 0<=nx<N:
                if chk[ny][nx] == False and L<=abs(graph[ny][nx]-graph[py][px])<=R:
                    chk[ny][nx] = True
                    chk2[ny][nx] = True
                    sw = True
                    population += graph[ny][nx]
                    coordinate.append((ny, nx))
                    queue.append((ny, nx))


# 실행
while True:
    sw = False
    chk = [[False]*N for _ in range(N)]

    for j in range(N):
        for i in range(N):
            if chk[j][i] == False and chk2[j][i] == False:
                chk[j][i] = True
                chk2[j][i] = True
                population = graph[j][i]
                coordinate = [(j, i)]

                bfs(j, i)

                if population != graph[j][i]:
                    divided = int(population/len(coordinate))
                    for c in coordinate:
                        graph[c[0]][c[1]] = divided
                        chk2[c[0]][c[1]] = False

    if sw == True:
        day += 1
    else:
        break


print(day)