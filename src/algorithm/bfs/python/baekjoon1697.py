"""
1. 아이디어
큐 자료구조를 이용해서
time을 지니고 넘긴다
그러다가 K 에 도달하면 stop

가본 곳 체크해서 또 가지 않도록 한다

2. 시간복잡도


3. 자료구조
time
queue
chk

"""

import sys
from collections import deque

input = sys.stdin.readline

N, K = map(int, input().split())

chk = [0 for _ in range(1000001)]

queue = deque()
queue.append(N)


while (queue):
    N = queue.popleft()

    if N < 0 or N > 100000:
        continue

    if N == K:
        print(chk[N])
        break

    for i in [N-1, N+1, N*2]:
        if (0<=i<=100000):
            if chk[i] == 0:
                chk[i] = chk[N] + 1
                queue.append(i)



