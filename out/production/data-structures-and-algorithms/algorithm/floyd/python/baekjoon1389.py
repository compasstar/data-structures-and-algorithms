"""
백준 1389번 케빈 베이컨의 6단계 법칙

1. 아이디어
- 모든 사람 -> 모든 사람으로 가는 것 : 플로이드
- 거리는 1로 한다

2. 시간복잡도
- V^3 = e6

3. 자료구조
- rs: int[][]
- cavin: 캐빈 베이건 수 저장할 리스트

"""
import sys
input = sys.stdin.readline
INF = sys.maxsize

N, M = map(int, input().split()) # node, edge
rs = [[INF] * (N+1) for _ in range(N+1)]

for i in range(1, N+1):
    rs[i][i] = 0

for _ in range(M):
    A, B = map(int, input().split())
    rs[A][B] = min(rs[A][B], 1)
    rs[B][A] = min(rs[B][A], 1)

for k in range(1, N+1):
    for j in range(1, N+1):
        for i in range(1, N+1):
            if rs[j][i] > rs[j][k] + rs[k][i]:
                rs[j][i] = rs[j][k] + rs[k][i]

cavin = INF
answer = 0
for i in range(1, N+1):
    cavin_i = 0
    for j in range(1, N+1):
        cavin_i += rs[i][j]
    if cavin > cavin_i:
        cavin = cavin_i
        answer = i

print(answer)


