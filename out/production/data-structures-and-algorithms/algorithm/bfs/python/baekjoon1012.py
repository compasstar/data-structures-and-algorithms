"""
1. 아이디어
- BFS
- 지도를 그린다
- 이어져있는 부분은 돌아다니며 chk를 True로 바꾼다
- 처음부터 마지막좌표까지 전부 돌아다닌다

2. 시간복잡도
- O(V + E)
- V = 50 * 50 = 2500
- E = 4V = 10000
- O(V+E) = 12500 < 2억

3. 자료구조
- farm 농장 지도
- chk 탐험 했는지 확인
- queue

"""

import sys
input = sys.stdin.readline



def cabbage():
    M, N, K = map(int, input().split())



    farm = [[0]*M for _ in range(N)]
    chk = [[False]*M for _ in range(N)]
    for _ in range(K):
        x, y = map(int, input().split())
        farm[y][x] = 1


    def BFS(y, x):
        dy = [1, 0, -1, 0]
        dx = [0, 1, 0, -1]
        queue = [(y, x)]

        while (queue):
            py, px = queue.pop()

            for d in range(4):
                ey = py + dy[d]
                ex = px + dx[d]
                if ((0 <= ey < N) and (0 <= ex < M)) and (chk[ey][ex] == False):
                    chk[ey][ex] = True
                    if farm[ey][ex] == 1:
                        queue.insert(0, (ey, ex))

    count = 0

    for j in range(N):
        for i in range(M):

            if chk[j][i] == False and farm[j][i] == 1:
                chk[j][i] = True
                BFS(j, i)
                count += 1

    return count



test_num = int(input())

for _ in range(test_num):

    count = cabbage()
    print(count)
