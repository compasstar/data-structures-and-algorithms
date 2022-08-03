"""
백준 2887번 행성터널

1. 아이디어
- 모든 행성 거리를 계산하면 시간초과가 난다
- 좌표를 토대로 인접한 행성만 계산한다
- MST - 크루스칼 로 최소 비용을 계산한다

2. 시간복잡도
- 인접한 행성 거리 리스트 -> O(N)
- 크루스칼  O(eloge)

3. 자료구조
- edge
- chk
- heap
- rs

"""
import sys, heapq

input = sys.stdin.readline


def union(x, y):
    x = find(x)
    y = find(y)
    parents[y] = x

def find(x):
    if x != parents[x]:
        parents[x] = find(parents[x])
    return parents[x]


N = int(input())

x_list = []
y_list = []
z_list = []

for i in range(N):
    x, y, z = list(map(int, input().split()))
    x_list.append([x, i])
    y_list.append([y, i])
    z_list.append([z, i])

x_list.sort()
y_list.sort()
z_list.sort()

edges = []

for lis in x_list, y_list, z_list:
    for i in range(1, N):
        distance = abs(lis[i - 1][0] - lis[i][0])
        edges.append([distance, lis[i - 1][1], lis[i][1]])

edges.sort(reverse=True)


# 크루스칼
parents = [i for i in range(N)]
cnt = N-1
rs = 0

while cnt > 0:

    w, a, b = edges.pop()
    if find(a) == find(b):
        continue
    union(a, b)
    cnt -= 1
    rs += w

print(rs)
