"""
백준 1956번 운동

1. 아이디어
- j -> i 와 i -> j 를 합한 값이 가장 작은 것을 구한다 : 플로이드

2. 시간복잡도
- O(V^3) = 400^3 = 64e6

3. 자료구조
- rs = int[][]


"""
import sys
input = sys.stdin.readline
INF = sys.maxsize

V, E = map(int, input().split())
rs = [[INF] * (V+1) for _ in range(V+1)]
for i in range(1, V+1):
    rs[i][i] = 0

for _ in range(E):
    a, b, c = map(int, input().split())
    rs[a][b] = min(rs[a][b], c)

for k in range(1, V+1):
    for j in range(1, V+1):
        for i in range(1, V+1):
            if rs[j][i] > rs[j][k] + rs[k][i]:
                rs[j][i] = rs[j][k] + rs[k][i]

length = INF
for i in range(1, V+1):
    for j in range(1, V+1):
        if i == j:
            continue
        length = min(length, rs[j][i] + rs[i][j])

if length == INF:
    print(-1)
else:
    print(length)

