"""
백준 14621번 나만 안되는 연애

1. 아이디어
- 엣지가 한정되어 있으므로 크루스칼 알고리즘이 더 빠르다
- 엣지를 구성할 때, 남학교와 여학교가 이어진 엣지만을 삽입한다
- 나머지는 크루스칼 알고리즘을 이용하여 길이를 측정한다
- 연결 경로가 없을 경우 -1을 출력한다.
    -> cnt가 0보다 큰데도, edges 에서 pop할 것이 없다면 -1 출력

2. 시간복잡도
- ElogE = e4loge4

3. 자료구조
- 크루스칼: find, union, edges, parents, rs, cnt


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

parents = [i for i in range(N+1)]
sex = list(input().split())
sex.insert(0, 0)
edges = []

for _ in range(M):
    u, v, d = map(int, input().split())
    if sex[u] == sex[v]:
        continue
    edges.append([d, u, v])

edges.sort(reverse=True)
cnt = N - 1
rs = 0

while cnt > 0:
    if edges == []:
        print(-1)
        exit()
    w, a, b = edges.pop()
    if find(a) == find(b):
        continue
    union(a, b)
    cnt -= 1
    rs += w

print(rs)