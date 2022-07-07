"""
백준 2531번 회전초밥

1. 아이디어
- k개의 접시를 선택해서 초밥벨트를 돌면서 가장 긴 값을 구한다
- 가장 긴 리스트에서 쿠폰번호가 없다면 +1을 하여 최댓값으로 정한다

2. 시간복잡도
- O(N)

3. 자료구조
- 초밥 벨트 belt = int[N]
- k개 고른 리스트 list_k = int[k]
- 가장 큰 값 value = int[N]
"""
import sys
from collections import deque
input = sys.stdin.readline

N, d, k, c = map(int, input().split())
belt = [int(input()) for _ in range(N)]
value = 0

list_k = deque()

for i in range(k):
    list_k.append(belt[i])

for i in range(k, N+k):

    list_k.popleft()
    if i > N-1:
        tmp = i - N
    else:
        tmp = i
    list_k.append(belt[tmp])

    set_list_k = set(list_k)
    len_set = len(set_list_k)
    if c not in set_list_k:
        len_set += 1

    value = max(value, len_set)


print(value)