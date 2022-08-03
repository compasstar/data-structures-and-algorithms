"""
백준 17472번 다리만들기

1. 아이디어
- 각 섬 간의 거리를 구한다 (핵심)
    - bfs 로 각 섬에 숫자를 넣어준다 O(N*M)
    - 각 좌표에서 상하좌우로 탐색하며 섬간의 거리를 인접리스트 형태로 삽입한다 (N+M * N*M)

- 거리를 바탕으로 MST 크루스칼 알고리즘으로 최소 비용을 구한다 O(ElgE)

2. 시간복잡도

3. 자료구조
- region
- bfs: chk, q
- 크루스칼: union, find, parents, rs, cnt,


"""
import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
region = [list(map(int, input().split())) for _ in range(N)]
chk = [[False] * M for _ in range(N)]


# 1. bfs 로 섬에 숫자 붙이기
dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]

def bfs(y, x, num):
    q = deque()
    q.append((y, x))
    chk[y][x] = True

    while q:
        py, px = q.popleft()
        region[py][px] = num

        for d in range(4):
            ey = py + dy[d]
            ex = px + dx[d]

            if 0<=ey<N and 0<=ex<M:
                if chk[ey][ex] == False and region[ey][ex] == 1:
                    chk[ey][ex] = True
                    q.append((ey, ex))

num = 1
for j in range(N):
    for i in range(M):
        if region[j][i] != 0:
            if chk[j][i] == False:
                bfs(j, i, num)
                num += 1


#for i in range(N):
#    print(region[i])


# 2. 섬 간에 거리 측정하여 인접리스트 형태로 만들기 (w, 섬1, 섬2)
edges = []
for j in range(N):
    for i in range(M):

        num = region[j][i]
        if num == 0:
            continue

        for d in range(4):
            py, px = j, i
            w = 0

            while True:
                py += dy[d]
                px += dx[d]
                if 0 <= py < N and 0 <= px < M:
                    if region[py][px] == 0:
                        w += 1

                    else:
                        if region[py][px] != num and w > 1: # 동일한 섬이여도 그냥 break 해버린다
                            edges.append([w, num, region[py][px]])
                        break

                else:
                    break



# 3. MST-크루스칼 이용하여 최소 거리 측정하기
edges.sort(reverse=True)

#print(edges)

land = 0
for j in range(N):
    for i in range(M):
        land = max(region[j][i], land)



parents = [i for i in range(land+1)]
rs = 0
cnt = land - 1

def union(x, y):
    x = find(x)
    y = find(y)
    parents[y] = x

def find(x):
    if x != parents[x]:
        parents[x] = find(parents[x])
    return parents[x]

while cnt > 0:

    if edges == []:
        print(-1)
        exit()
    w, a, b = edges.pop()
    if find(a) == find(b):
        continue
    union(a, b)
    cnt -= 1
    rs += w

print(rs)
