"""
백준 15650번

1. 아이디어
- 백트래킹으로 N까지 돌면서 길이가 M이면 출력

2. 시간복잡도
- O(N!)

3. 자료구조
- 수열 저장 rs = int[]
- 방문여부 chk = bool[]
"""
import sys
input = sys.stdin.readline

N, M = map(int, input().split())

rs = []
chk = [False]*(N+1)

def recur(num):
    if num == M:
        print(' '.join(map(str, rs)))
        return

    for i in range(1, N+1):
        if chk[i] == False:
            if len(rs) == 0 or rs[-1] < i:
                chk[i] = True
                rs.append(i)
                recur(num+1)
                chk[i] = False
                rs.pop()


recur(0)