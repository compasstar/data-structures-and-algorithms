"""
백준 10815번 숫자카드

1. 아이디어
- 이분탐색으로 해당 숫자가 존재하면 1을, 아니면 0을 리턴한다

2. 시간복잡도
- M * logN = e5 * loge5

3. 자료구조
- N_nus, M_nums
- def binary_search


"""
import sys
input = sys.stdin.readline

N = int(input())
N_nums = list(map(int, input().split()))
M = int(input())
M_nums = list(map(int, input().split()))
N_nums.sort()

def binary_search(target):

    st, en = 0, N-1

    while st < en:

        mid = (st + en) // 2

        if N_nums[mid] < target:
            st = mid + 1
        else:
            en = mid


    if N_nums[en] == target:
        return 1
    else:
        return 0


answer = []
for m in M_nums:
    answer.append(binary_search(m))

print(" ".join(map(str, answer)))
