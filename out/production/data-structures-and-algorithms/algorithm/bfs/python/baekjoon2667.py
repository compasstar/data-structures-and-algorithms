"""
1. 아이디어
- 큐 구조로 단지가 몇개 있는지 확인한다
- BFS
- 2중 for문 돌면서 방문안했다면 그곳 주위를 찾아서 1이 몇개 있는지 세기

2. 시간복잡도
- O(V + E)
- V = N^2   (5<=N<=25)
- E = V*4
- O(V+E) = N^2 * 5 < 1억

3. 자료구조
- 지도 map
- 방문했는지 확인 chk
- BFS(queue)
- 단지 수 저장할 리스트 house
"""

import sys
input = sys.stdin.readline

n = int(input())

mab = []
for _ in range(n):
    tmp = list(input())
    tmp.pop()
    tmp = list(map(int, tmp))
    mab.append(tmp)

chk = [[False]*n for _ in range(n)]

house = []

dy = [1,0,-1,0]
dx = [0,1,0,-1]

def bfs(y, x):
    if mab[y][x] == 0 or chk[y][x] == True:
        return 0;

    chk[y][x] = True
    
    cnt = 0
    queue = [(y, x)]
    while (queue):
        ey, ex = queue.pop()
        cnt += 1
        for d in range(4):
            ny = ey + dy[d]
            nx = ex + dx[d]
            if 0 <= ny < n and 0 <= nx < n:
                if chk[ny][nx] == False and mab[ny][nx] == 1:
                    chk[ny][nx] = True
                    queue.insert(0, (ny, nx))
    return cnt



for j in range(n):
    for i in range(n):
        cnt = bfs(j, i)
        if cnt != 0:
            house.append(cnt)

house.sort()
print(len(house))
for i in range(len(house)):
    print(house[i])


