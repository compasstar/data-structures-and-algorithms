"""
1. 아이디어

- 1*1 ~ 1*N
- 2*1 ~ 2*N
...
- N*1 ~ N*N

- 각 행에서 B[k]보다 작은 값의 개수는 min(B[k]//i, N)
- B[k]를 구하라
- k = min(B[k]//i, N) 각 행 별로 합

- 이분탐색으로 1 ~ k
- check: k 값 구하기

"""

import sys
input = sys.stdin.readline

N = int(input())
k = int(input())

def Checked(mid):
    sum_k = 0
    for i in range(1, N+1):
        sum_k += min(mid//i, N)
    return sum_k

start = 0
end = k + 1
while (start + 1 < end):
    mid = (start + end) // 2
    if (Checked(mid) < k):
        start = mid
    else:
        end = mid
print(end)
