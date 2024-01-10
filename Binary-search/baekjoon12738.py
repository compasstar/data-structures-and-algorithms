"""
1. 아이디어
- 가장 긴 증가하는 부분 수열
- LIS

2. 시간복잡도
- N = 1,000,000
- N * log(N) = 1,000,000 * log(1,000,000)

3. 로직
- LIS 끝 값보다 크면 append
- 아니라면 LIS 에서 해당 값 이상 중 가장 작은 값 위치 찾아서 교체

"""
import sys
input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split()))

LIS = []


def binary_search(a):
    start = -1
    end = len(LIS)
    while (start + 1 < end):
        mid = (start + end) // 2
        if (LIS[mid] < a):
            start = mid
        else:
            end = mid
    LIS[end] = a

for a in A:
    if (len(LIS) == 0 or LIS[-1] < a):
        LIS.append(a)
    else:
        # 이분 탐색으로 찾아서 값 변경
        binary_search(a)

print(len(LIS))
        
