"""
백준 7569번 토마토

1. 아이디어
- 3차원 창고에서 bfs를 실행한다
- 처음에 익은 토마토가 있는 위치로 queue를 만든다
- queue가 빌 때까지 bfs를 통해 딱 한 칸씩만 이동하여 익은 토마토를 전파시킨다. 그러면서 새로운 큐를 만든다
- 새로운 큐를 기준으로 다시 이 큐가 빌 떄까지 딱 한칸씩만 이동하여 익은 토마토를 전파시킨다
- 위 과정을 반복하며 날짜를 센다

2. 시간복잡도
- O(V+E) 
- 4e6

3. 자료구조
- container = int[][][]
- chk = int[][][]
- tomato = deque()

"""

import sys
from collections import deque
input = sys.stdin.readline

M, N, H = map(int, input().split())
container = [[list(map(int, input().split())) for _ in range(N)] for _ in range(H)]

tomato = deque()
for k in range(H):
  for j in range(N):
    for i in range(M):
      if container[k][j][i] == 1:
        tomato.append([k, j, i])

chk = [[[False] * M for _ in range(N)] for _ in range(H)]
new_tomato = deque()

dz = [1, -1, 0, 0, 0, 0]
dy = [0, 0, 0, 0, 1, -1]
dx = [0, 0, -1, 1, 0, 0]

def bfs(z, y, x):  
  chk[z][y][x] = True

  for d in range(6):
    ez = z + dz[d]
    ey = y + dy[d]
    ex = x + dx[d]

    if 0<=ez<H and 0<=ey<N and 0<=ex<M:
      if container[ez][ey][ex] == 0 and chk[ez][ey][ex] == False:
        chk[ez][ey][ex] = True
        new_tomato.append([ez, ey, ex])

          
          
day = -1
while tomato:
  while tomato:
    tz, ty, tx = tomato.popleft()
    bfs(tz, ty, tx)
  
  tomato = new_tomato
  new_tomato = deque()
  day += 1



for k in range(H):
  for j in range(N):
    for i in range(M):
      if chk[k][j][i] == False and container[k][j][i] == 0:
        print(-1)
        exit()
        
print(day)
