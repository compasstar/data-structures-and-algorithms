"""
백준 11403번 경로 찾기

1. 아이디어
- 모든 정점에 관하여 모든 도착점 찾기 -> 플로이드

2. 시간복잡도
- O(V^3) = 100^3 = e6

3. 자료구조
- rs = int[][]

"""
import sys
input = sys.stdin.readline
INF = sys.maxsize

N = int(input())
rs = [list(map(int, input().split())) for _ in range(N)]


for k in range(N):
    for j in range(N):
        for i in range(N):
            if rs[j][i] == 0:
                if rs[j][k] == 1 and rs[k][i] == 1:
                    rs[j][i] = 1

for j in range(N):
    for i in range(N):
        print(rs[j][i], end = " ")
    print()