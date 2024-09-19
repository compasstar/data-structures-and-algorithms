"""
백준 14503번

1. 아이디어
- while문으로, 특정조건 종료될때까지 반복
- 청소하면 맵을 2로 바꾸기
- 4방향을 for문으로 탐색
- 더이상 탐색 불가능할경우, 뒤로 한칸 후진
- 후진이 불가능하면 종료

2. 시간복잡도
- O(N*N): 50^2 = 2500 < 2억

3. 자료구조
- map: int[][]
- 로봇총소기 위치, 방향 (y, x, d)
- 전체 청소한 곳 수 cnt = int

"""

import sys
input = sys.stdin.readline

N, M = map(int, input().split())
y, x, d = map(int, input().split())
mab = [list(map(int, input().split())) for _ in range(N)]
cnt = 0
dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]


while 1:

    if mab[y][x] == 0:
        mab[y][x] = 2
        cnt += 1
    sw = False

    for i in range(1, 5):
        ny = y + dy[d-i]
        nx = x + dx[d-i]
        if 0<=ny<N and 0<=nx<M:
            if mab[ny][nx] == 0:
                d = (d-i+4) % 4
                y = ny
                x = nx
                sw = True
                break

    # 4방향 모두 있지 않은 경우
    if sw == False:
        # 뒤쪽 방향이 막혀있는지 확인
        ny = y - dy[d]
        nx = x - dx[d]
        if 0<=ny<N and 0<=nx<M:
            if mab[ny][nx] == 1:
                break
            else:
                y = ny
                x = nx
        else:
            break


print(cnt)


