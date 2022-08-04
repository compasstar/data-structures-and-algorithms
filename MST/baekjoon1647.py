"""
백준 1647번 도시 분할 계획

1. 아이디어
- 크루스칼 알고리즘을 이용하여 가장 큰 w 값 하나를 빼준면 됨

2. 시간복잡도
- eloge = e6loge6

3. 자료구조
- 크루스칼: find, union, parents, edges, rs, cnt


"""
import sys
input = sys.stdin.readline

def find(x):
    if x != parents[x]:
        parents[x] = find(parents[x])
    return parents[x]

def union(x, y):
    x = find(x)
    y = find(y)
    parents[y] = x


N, M = map(int, input().split())
edges = []
for _ in range(M):
    A, B, C = map(int, input().split())
    edges.append([C, A, B])

edges.sort(reverse=True)
parents = [i for i in range(N+1)]
cnt = N-1
rs = []

while cnt > 0:
    w, a, b = edges.pop()

    if find(a) == find(b):
        continue

    union(a, b)
    cnt -= 1
    rs.append(w)

print(sum(rs[:-1]))