"""
백준 3272번 두 수의 합

1. 아이디어
- 2중 for문으로 돌리면 10^5 ** 2 = 10^10 시간초과 -> 투포인터

- 정렬한다
- 포인터를 양 끝으로 잡는다
- 두 포인터의 합이 x보다 크다면 오른쪽 포인터를 왼쪽으로 옮긴다
- 두 포인터의 합이 x보다 작다면 왼쪽 포인터를 오른쪽으로 옮긴다
- 두 포인터가 만날 때까지 반복한다

2. 시간복잡도
- 정렬 NlogN
- 포인터 이동 N

3. 자료구조
- 수열 nums
- 카운트 cnt
"""
import sys
input = sys.stdin.readline

N = int(input())
nums = list(map(int, input().split()))
nums.sort()
x = int(input())
cnt = 0

first = 0
last = len(nums)-1

while first < last:
    s = nums[first] + nums[last]

    if s == x:
        cnt += 1
        first += 1
    elif s < x:
        first += 1
    elif s > x:
        last -= 1

print(cnt)

