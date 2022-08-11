"""
백준 2660번 회장뽑기

1. 아이디어
- 점수가 높을수록 통해서 가는게 더 많다
- 회장은 점수가 가장 적다
- 플로이드를 통해 표를 만들고 0에서 시작한다 거칠수록 점수가 커진다
    - 자신이 가지는 max값이 본인의 점수가 된다
        - 이 중에서 가장 점수가 낮은 사람을 찾는다

2. 시간복잡도
- O(V^3) = 50^3 = 125e3

3. 자료구조
- rs = int[][]


"""
import sys
input = sys.stdin.readline
INF = sys.maxsize

N = int(input())
rs = [[INF] * (N+1) for _ in range(N+1)]

for i in range(1, N+1):
    rs[i][i] = 0

while True:
    a, b = map(int, input().split())
    if a == -1 and b == -1:
        break

    rs[a][b] = 1
    rs[b][a] = 1

for k in range(1, N+1):
    for j in range(1, N+1):
        for i in range(1, N+1):
            if rs[j][i] > rs[j][k] + rs[k][i]:
                rs[j][i] = rs[j][k] + rs[k][i]



chairman = [100]
for j in range(1, N+1):
    max_value = 0
    for i in range(1, N+1):
        max_value = max(max_value, rs[j][i])
    chairman.append(max_value)

print(min(chairman), chairman.count(min(chairman)))
answer = []
for i in range(1, N+1):
    if chairman[i] == min(chairman):
        answer.append(i)
print(" ".join(map(str, answer)))

