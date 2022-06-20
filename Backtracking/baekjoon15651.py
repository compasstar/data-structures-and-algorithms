"""
백준  15651번

1. 아이디어
- 백트래킹으로 모든 수열을 구한다

2. 시간복잡도
- O(N^N)

3. 자료구조
- 수열 result = int[]

"""
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
result = []

def recur(num):
    if num == M:
        print(" ".join(map(str, result)))
        return

    for i in range(1, N+1):
        result.append(i)
        recur(num+1)
        result.pop()

recur(0)