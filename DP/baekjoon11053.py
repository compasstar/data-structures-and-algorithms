"""
백준 11053번 가장 긴 증가하는 부분 수열

1. 아이디어
- 리스트를 하나 만들어 A를 돌면서 다음 규칙에 의해 숫자를 추가하거나 수정한다
- A의 숫자가 가장 크면 그저 append 한다
- A의 숫자가 가장 크지 않다면 해당 숫자보다 다음으로 큰 숫자와 바꾼다
- 리스트의 길이가 가장 긴 증가하는 부분 수열이다

2. 시간복잡도
- A를 돈다 -> 1000
- 리스트에서 위치를 찾는다 -> 1000
- 1000 * 1000 = 1000000

3. 자료구조
- LIS[]
- A

"""
import sys
input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split()))

LIS = []
LIS.append(A[0])
A.pop(0)

def find_index(x):

    for i in range(N):
        if x <= LIS[i]:
            return i

for a in A:
    if a > LIS[-1]:
        LIS.append(a)
    else:
        index = find_index(a)
        LIS[index] = a

print(len(LIS))
