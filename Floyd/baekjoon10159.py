"""
백준 10159번 저울

1. 아이디어
- 플로이드로 모든 쌍을 구한뒤, 만약 연결이 되어있다면 1을 아니면 0을 입력한다
- 본인이 가는 것과 본인에게 오는 것의 합을 구하고 N-1에서 뺀다

2. 시간복잡도
- O(V^3) = e6

3. 자료구조
- rs = int[][]


"""
import sys
input = sys.stdin.readline

N = int(input())
M = int(input())
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

unknown = []
for j in range(1, N+1):
    cnt = N+1
    for i in range(1, N+1):
        if rs[j][i] == 1:
            cnt -= 1
        if rs[i][j] == 1:
            cnt -= 1
    unknown.append(cnt)

for u in unknown:
    print(u)
