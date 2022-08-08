"""
백준 4485번 녹색 옷 입은 애가 젤다지?

1. 아이디어
- 다익스트라로 모든 점에 대하여 거리를 잰다

2. 시간복잡도
- VlogE = N*N log 4N*N = 125*125 log 4 * 125*125

3. 자료구조
- edges = int[N][N]
- dist = int[N][N]
- heap


"""
import sys, heapq
INF = sys.maxsize
input = sys.stdin.readline




def main(N):
    edges = [ list(map(int, input().split())) for _ in range(N) ]
    dist = [[INF] * N for _ in range(N)]
    dist[0][0] = edges[0][0]
    heap = [[dist[0][0], 0, 0]]

    dy = [1, 0, -1, 0]
    dx = [0, 1, 0, -1]
    while heap:
        w, y, x = heapq.heappop(heap)
        if w != dist[y][x]:
            continue

        for d in range(4):
            ey = y + dy[d]
            ex = x + dx[d]

            if 0<=ey<N and 0<=ex<N:
                if dist[ey][ex] > w + edges[ey][ex]:
                    dist[ey][ex] = w + edges[ey][ex]
                    heapq.heappush(heap, [dist[ey][ex], ey, ex])

    print("Problem {}: {}".format(cnt, dist[N-1][N-1]))

cnt = 1
while True:
    N = int(input())
    if N == 0:
        break
    else:
        main(N)
        cnt += 1
