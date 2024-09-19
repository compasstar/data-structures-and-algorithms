"""
백준 9095번 1, 2, 3 더하기

1. 아이디어

7은 6의 경우의 수에 끝에 1을 추가한 것
    5의 경우의 수에 끝에 2를 추가한 것
    4의 경우의 수에 끝에 3을 추가한 것으로 나눌 수 있다

An = An-1 + An-2 + An-3

- DP -> 점화식을 통해 계산한다

2. 시간복잡도
- O(N)

3. 자료구조
- nums = int[]

"""
import sys
input = sys.stdin.readline

def main():
    n = int(input())

    nums = [0, 1, 2, 4]

    for i in range(4, n+1):
        nums.append(nums[i-1] + nums[i-2] + nums[i-3])

    print(nums[n])


T = int(input())
for _ in range(T):
    main()
