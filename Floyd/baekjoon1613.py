"""
백준 1613번

1. 아이디어
- 순서대로 그래프를 연결하는 문제임 그래서 INF가 있는지 확인: 플로이드
- 연결관계있다면 1, 없다면 0으로 표현하겠음
- a, b 가 주어졌을 때, rs[a][b] 가 1이라면 -1, rs[b][a]가 1이라면 1,
    - 둘 다 0라면 0

2. 시간복잡도
- O(V^3)

3. 자료구조
- rs = int[][]

"""
import sys
input = sys.stdin.readline
INF = sys.maxsize

n, k = map(int, input().split())
rs = [[0] * (n+1) for _ in range(n+1)]
for i in range(1, n+1):
    rs[i][i] = 1

for _ in range(k):
    a, b = map(int, input().split())
    rs[a][b] = 1

for k in range(1, n+1):
    for j in range(1, n+1):
        for i in range(1, n+1):
            if rs[j][i] == 0:
                if rs[j][k] == 1 and rs[k][i] == 1:
                    rs[j][i] = 1

s = int(input())
for _ in range(s):
    a, b = map(int, input().split())
    if rs[a][b] == 1:
        print(-1)
    elif rs[b][a] == 1:
        print(1)
    else:
        print(0)