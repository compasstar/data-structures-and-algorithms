"""
백준 15652번

1. 아이디어
- 백트래킹으로 모든 수열을 출력한다

2. 시간복잡도
- O(N!)

3. 자료구조
- results = int[]
"""
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
results = []

def recur(num):
    if num == M:
        print(" ".join(map(str, results)))
        return

    for i in range(1, N+1):

        if len(results) == 0 or results[-1] <= i:
            results.append(i)
            recur(num+1)
            results.pop()

recur(0)