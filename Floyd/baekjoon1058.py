"""
백준 1058번 친구

1. 아이디어
- 플로이드를 통해 2-친구 수를 구한다
- 친구수과 2-친구 수를 더하여 합을 구한다
- 2차원 배열에서: 2친구라면 N을 2로 바꾼다
- 합의 max값을 찾아 가장 유명한 사람의 친구를 출력한다

2. 시간복잡도
- O(V^3) = 50^3

3. 자료구조
- rs = int[][]


"""
import sys
input = sys.stdin.readline
INF = sys.maxsize

N = int(input())
rs = [list(input().strip()) for _ in range(N)]

for k in range(N):
    for j in range(N):
        for i in range(N):
            if rs[j][i] == 'N':
                if rs[j][k] == 'Y' and rs[k][i] == 'Y':
                    rs[j][i] = 2

# 본인은 빼주어야 한다
for i in range(N):
    rs[i][i] = 'N'

max_friend = 0
for j in range(N):
    v = 0
    for i in range(N):
        if rs[j][i] == 'Y' or rs[j][i] == 2:
            v += 1
    max_friend = max(max_friend, v)

print(max_friend)

