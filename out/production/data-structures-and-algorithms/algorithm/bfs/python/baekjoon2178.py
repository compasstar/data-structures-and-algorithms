"""
1. 아이디어
- BFS
- int move = 0
-

2. 시간복잡도
- BFS: O(V + E)
- V = N * M =
- E =
- V + E =

3. 자료구조
- 그래프
- 방문: boolean
- Queue(BFS)



"""

import sys
input = sys.stdin.readline


n, m = map(int, input().split())

# 1. 그래프
miro = []
for _ in range(n):
    l = list(input())
    l.pop()
    l = list(map(int, l))

    miro.append(l)


# 2. 방문
chk = [[False] * m for _ in range(n)]

# 3. Queue
x, y = 0, 0
chk[0][0] = True

queue = [(y, x, 1)] # x, y, move
dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]

while queue:
    ey, ex, move = queue.pop()

    if (ey, ex) == (n-1, m-1):
        break

    for i in range(4):
        ny = ey + dy[i]
        nx = ex + dx[i]
        if 0<=ny<n and 0<=nx<m:
            if miro[ny][nx] == 1 and chk[ny][nx] == False:
                queue.insert(0,(ny, nx, move+1))
                chk[ny][nx] = True


print(move)
