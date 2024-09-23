"""
백준 12015번 가장 긴 증가하는 부분 수열 2

1. 아이디어
- LIS 리스트를 만든다
- A를 돌면서 다음 과정을 실행한다
a) 만일 마지막 인덱스 수 보다 크다면 append 한다
b) 만일 마지막 인덱스 보다 작다면 제자리를 찾아서 교체한다
    - 제자리란? LIS 리스트 수 중에 해당 수 이상인 첫번째 수와 교체한다
- LIS 의 길이를 출력한다

2. 시간복잡도
- A를 한바퀴 돈다 -> e6
- 하나 실행할 때마다 이분탐색 실행 -> loge6
- O(e6loge6)

3. 자료구조
- A
- LIS
- def binary_search(st, en, target)

"""
import sys
input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split()))

# 타겟과 같거나 큰 첫번째 인덱스 구하기
def binary_search(st, en, target):

    while st <= en:
        mid = (st + en) // 2

        if LIS[mid] < target:
            st = mid + 1
        else:
            en = mid - 1

    return st

LIS = [A[0]]

for a in A:
    if LIS[-1] < a:
        LIS.append(a)
    else:
        index = binary_search(0, len(LIS)-1, a)
        LIS[index] = a


print(len(LIS))

