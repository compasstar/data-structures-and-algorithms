"""
백준 1774번 우주신과의 교감

1. 아이디어
- 그래프 형태로 만들자면 노드는 1000개, 엣지는 e6개 -> 시간복잡도 e6loge6 괜찮다
-> 그래프 형태로 만들고 MST-Prim 알고리즘을 사용하여 최소 길이를 구한다

2. 시간복잡도
- ElogE -> e6loge6

3. 자료구조
- space
- Prim: edges, heap, rs, chk


"""
import sys, heapq, math
input = sys.stdin.readline

N, M = map(int, input().split())
space = [list(map(int, input().split())) for _ in range(N)]
edges = [[] for _ in range(N+1)]
chk = [False] * (N+1)
rs = 0

# 좌표 별로 거리를 측정하여 그래프를 만들어준다
for i in range(N):
    for j in range(i):
        distance = math.sqrt((space[i][0] - space[j][0])**2 + (space[i][1] - space[j][1])**2)
        edges[i+1].append([distance, j+1])
        edges[j+1].append([distance, i+1])

# 이미 연결된 것은 거리를 0으로 해서 edges에 넣어준다
for _ in range(M):
    a, b = map(int, input().split())
    edges[a].append([0, b])
    edges[b].append([0, a])

# heap을 이용한 Prim 알고리즘 실행
heap = [[0, 1]]

while heap:
    w, node = heapq.heappop(heap)
    if chk[node] == False:
        chk[node] = True
        rs += w

        for e in edges[node]:
            if chk[e[1]] == False:
                heapq.heappush(heap, e)

print("{:.2f}".format(rs))
