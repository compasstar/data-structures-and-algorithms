"""
백준 14938번 서강그라운드

1. 아이디어
- 어디서 시작하는지를 알려주지 않았으므로 모든 노드 -> 모든 노드 계산
- 플로이드

2. 시간복잡도
- O(V^3) = e6

3. 자료구조
- rs = int[][]
- max_item = int


"""
import sys
input = sys.stdin.readline
INF = sys.maxsize

n, m, r = map(int, input().split()) # 노드, 수색범위, 길의 개수
item = list(map(int, input().split()))
item.insert(0, 0)

rs = [[INF] * (n+1) for _ in range(n+1)]

for i in range(1, n+1):
    rs[i][i] = 0

for _ in range(r):
    a, b, l = map(int, input().split())
    rs[a][b] = min(rs[a][b], l)
    rs[b][a] = min(rs[b][a], l)

for k in range(1, n+1):
    for j in range(1, n+1):
        for i in range(1, n+1):
            if rs[j][i] > rs[j][k] + rs[k][i]:
                rs[j][i] = rs[j][k] + rs[k][i]

max_value = 0
for j in range(1, n+1):
    item_value = 0
    for i in range(1, n+1):
        if rs[j][i] <= m:
            item_value += item[i]
    max_value = max(max_value, item_value)

print(max_value)

