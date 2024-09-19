"""
1. 아이디어
- BFS
- 지도에서 동시다발적으로 1이라면 주위에 퍼뜨린다 만일 -1 라면 못퍼짐
- 퍼지면서 반복문 한 번 돌때마다 day += 1


2. 시간복잡도
- O(V+E)
- V = N * M

3. 자료구조
- farm 토마토 지도
- 1의 개수만큼의 queue 구조를 가진다
- queue
- day 날짜

"""

from collections import deque
import sys
input = sys.stdin.readline

M, N = map(int, input().split())
farm = [list(map(int, input().split())) for _ in range(N)]

queue = deque()
for j in range(N):
    for i in range(M):
        if farm[j][i] == 1:
            queue.append((j, i))


dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]

while(queue):

    py, px = queue.popleft()

    for d in range(4):

        ey = py + dy[d]
        ex = px + dx[d]

        if 0<=ey<N and 0<=ex<M:
            if farm[ey][ex] == 0:
                farm[ey][ex] = farm[py][px] + 1
                queue.append((ey, ex))

answer = 0

for j in range(N):
    for i in range(M):
        if farm[j][i] == 0:
            print(-1)
            exit()

        answer = max(answer, farm[j][i])

print(answer-1)
