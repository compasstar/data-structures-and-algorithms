"""
1. 아이디어
- 가장 긴 증가하는 부분 수열

2. 시간복잡도
- N * logN = 40,000 * log(40,000)

3. 로직
- port 돌면서 끝 값보다 크면 append
- 아니면 위치 찾아서 값 교체
"""
import sys
input = sys.stdin.readline

N = int(input())
port = list(map(int, input().split()))

LIS = []

def binary_search(p):
    start = -1
    end = len(LIS)
    while (start + 1 < end):
        mid = (start + end) // 2
        if (LIS[mid] < p):
            start = mid
        else:
            end = mid
    LIS[end] = p


for p in port:
    if (len(LIS) == 0 or LIS[-1] < p):
        LIS.append(p)
    else:
        # p이상 값 위치 찾아서 p와 값 교체
        binary_search(p)

print(len(LIS))
