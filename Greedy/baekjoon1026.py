"""
백준 1026번 보물

1. 아이디어
- 가장 큰 값과 가장 작은 값을 곱하여 나간다
- A는 정렬, B는 역순정렬을 하여 S를 구한다

2. 시간복잡도
- 정렬 --> NlogN
- 합 --> N

3. 자료구조
- A, B

"""
import sys
input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

A.sort()
B.sort()
B.reverse()

S = 0

for i in range(N):
    S += A[i]*B[i]

print(S)

