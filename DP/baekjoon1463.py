"""
백준 1463번 1로 만들기

1. 아이디어

- 3으로 나누고, 안되면 2로 나누고 안되면 1을 빼는 방식은 성립 불가 -> 그리디 x
- 특별한 규칙을 모르므로 전부 시도해 보아야 한다 -> DP
- 3을 곱해보고 2를 곱해보고 1을 더해보며 시도 횟수가 가장 적은 것으로 입력한다

2. 시간복잡도
- O(N)

3. 자료구조
- nums = int[N+1]
"""

import sys
input = sys.stdin.readline

N = int(input())
nums = [0] * (N+1)

for i in range(2, N+1):

    nums[i] = nums[i-1] + 1

    if i % 3 == 0:
        nums[i] = min(nums[i], nums[i//3]+1)
    if i % 2 == 0:
        nums[i] = min(nums[i], nums[i//2]+1)

print(nums[N])

