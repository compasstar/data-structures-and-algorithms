"""
백준 2458번 키순서

1. 아이디어
- 자신이 갈 수 있는 노드의 개수 + 나에게 오는 노드의 개수 = N + 1
    - 그러면 순서를 알 수 있다

- 플로이드로 갈 수 있는지 확인하는 2차원 그래프를 그린다
    - 갈 수 있다면 1, 못간다면 0을 표시

- 갈 수 있는 것과 나에게 오는 것을 더하여 순서를 알 수 있는지 체크한다


2. 시간복잡도
- O(V^3) = 500^3 = 125000000 = 125 * e6 = 1.25 * e8

3. 자료구조
- rs = int[][]
- cnt


"""
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
rs = [[0] * (N+1) for _ in range(N+1)]
for i in range(1, N+1):
    rs[i][i] = 1

for _ in range(M):
    a, b = map(int, input().split())
    rs[a][b] = 1

for k in range(1, N+1):
    for j in range(1, N+1):
        for i in range(1, N+1):
            if rs[j][i] == 0:
                if rs[j][k] == 1 and rs[k][i] == 1:
                    rs[j][i] = 1

cnt = 0
for n in range(1, N+1):
    s = 0
    for i in range(1, N+1):
        if rs[n][i] == 1:
            s += 1
        if rs[i][n] == 1:
            s += 1
    if s == N+1:
        cnt += 1

print(cnt)
