"""
1. 아이디어
- k층에 n호에는 몇 명이 살고 있는지 구하라.
- DP
- 0층: 1호 1명 2호 2명
- 1층: 1호 1명 2호 1+2명
- 2층: 1호 1명 2호 1+(1+2)명
- 0층만 만들고 0층을 기준으로 계속 더해간다

2. 시간복잡도
- O(K * N^2)

3. 자료구조
- apart = [[] for _ in range(K+1)]
- 아파트에 0층은 미리 집어넣어 두고, 0층을 기준으로 계속해서 각 층을 더해간다
"""

import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    K = int(input()) # 층 수
    N = int(input()) # 호 수

    apart = [[] for _ in range(K + 1)]
    for i in range(1, N+1):
        apart[0].append(i)

    for k in range(1, K+1):
        for n in range(1, N+1):
            temp_sum = 0
            for i in range(n):
                temp_sum += apart[k-1][i]
            apart[k].append(temp_sum)
    print(apart[K][N-1])
                
        

           
