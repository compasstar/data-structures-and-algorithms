"""
백준 1912번 연속합

1. 아이디어
- dp 배열을 하나 더 만들어서 숫자를 계속 더해간다
- 앞의 인덱스는 여태까지 리스트의 합일 것이다
- 앞의 인덱스값이 음수라면 현재값을 기준으로 초기화시킨다

2. 시간복잡도
- O(N)

3. 자료구조
- nums = int[N]
"""
import sys
input = sys.stdin.readline

N = int(input())
nums = list(map(int, input().split()))

for i in range(1, N):
    if nums[i-1] > 0:
        nums[i] += nums[i-1]

print(max(nums))
