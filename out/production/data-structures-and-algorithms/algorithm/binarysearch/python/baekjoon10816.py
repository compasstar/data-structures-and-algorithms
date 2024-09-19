"""
백준 10816번 숫자 카드 2

1. 아이디어

- N 리스트를 정렬한 후, M의 첫째 숫자에 대해 이진탐색으로 같은 수를 찾아서
 같은 게 얼마나 있는지 센다
- 나머지 수들에 대해서도 반복한다

2. 시간복잡도
- O(MlogN)

3. 자료구조
- N_nums
- M_nums

"""
import sys
input = sys.stdin.readline

N = int(input())
N_nums = list(map(int, input().split()))
M = int(input())
M_nums = list(map(int, input().split()))

N_nums.sort()
answer = []

def binary_search(target, type):
    st, en = 0, N

    while st < en:

        mid = (st + en) // 2

        if type == 0:
            if N_nums[mid] < target:
                st = mid + 1
            else:
                en = mid
        else:
            if N_nums[mid] <= target:
                st = mid + 1
            else:
                en = mid

    return en


for m in M_nums:
    value = binary_search(m, 1) - binary_search(m, 0)
    answer.append(value)

print(" ".join(map(str, answer)))


