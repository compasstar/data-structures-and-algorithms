"""
백준 14888번

1. 아이디어
- 연산자를 백트래킹을 통해 전부 실행해보면서 최댓값과 최솟값을 구한다
- <+, -, *, />

2. 시간복잡도
- O(N!)

3. 자료구조
- 주어진 숫자 numbers = int[]
- 연산자 저장 sign = int[]
"""

import sys
input = sys.stdin.readline

N = int(input())
numbers = list(map(int, input().split()))
sign = list(map(int, input().split()))

result = numbers[0]
result_max = -1000000000
result_min = 1000000000

def recur(num):
    global result, result_max, result_min

    if num == N-1:
        result_max = max(result_max, result)
        result_min = min(result_min, result)
        return

    temp = result

    if sign[0] > 0:
        sign[0] -= 1
        result += numbers[num+1]
        recur(num+1)
        sign[0] += 1
        result -= numbers[num+1]
    if sign[1] > 0:
        sign[1] -= 1
        result -= numbers[num+1]
        recur(num + 1)
        sign[1] += 1
        result += numbers[num+1]
    if sign[2] > 0:
        sign[2] -= 1
        result *= numbers[num+1]
        result = int(result)
        recur(num + 1)
        sign[2] += 1
        result = temp
    if sign[3] > 0:
        sign[3] -= 1
        result /= numbers[num+1]
        result = int(result)
        recur(num + 1)
        sign[3] += 1
        result = temp

recur(0)

print(result_max)
print(result_min)