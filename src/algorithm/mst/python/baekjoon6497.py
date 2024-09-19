"""
백준 6497번 전력난

1. 아이디어
- 간선이 적게 사용되었다 -> 크루스칼 알고리즘 이용

2. 시간복잡도
- ElogE = e5loge5

3. 자료구조
- 크루스칼: find, union, parents, edges, rs, cnt

"""
import sys
input = sys.stdin.readline


def main(m, n):
    def find(x):
        if x != parents[x]:
            parents[x] = find(parents[x])
        return parents[x]

    def union(x, y):
        x = find(x)
        y = find(y)
        parents[y] = x

    total_cost = 0
    edges = []
    for i in range(n):
        x, y, z = map(int, input().split())
        edges.append([z, x, y])
        total_cost += z

    edges.sort(reverse=True)
    parents = [i for i in range(m)]
    rs = 0
    cnt = m - 1

    while cnt > 0:
        w, a, b = edges.pop()
        if find(a) == find(b):
            continue
        union(a, b)
        cnt -= 1
        rs += w

    print(total_cost - rs)


while True:
    m, n = map(int, input().split())  # m = 노드, n = 엣지
    if m == 0 and n == 0:
        break
    main(m, n)
