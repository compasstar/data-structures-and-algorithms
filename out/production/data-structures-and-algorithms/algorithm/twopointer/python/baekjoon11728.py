"""
백준 11728번 배열 합치기

1. 아이디어
- 합치고 정렬한다

2. 시간복잡도

3. 자료구조
- A, B
"""
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

C = A+B
C.sort()
print(" ".join(map(str, C)))