"""
백준 11399번 ATM

1. 아이디어
- 걸리는 시간 P를 정렬하고, 가장 적게 걸리는 사람을 가장 앞으로,
가장 오래 걸리는 사람을 맨뒤로 보낸다. 즉 정렬한다
- 사람 수에 따라서 걸리는 시간을 더해간다

2. 시간복잡도
- 정렬 -> NlogN
- 시간 더하기 -> N**2 = 1,000,000

3. 자료구조

"""
import sys
input = sys.stdin.readline

N = int(input())
P = list(map(int, input().split()))
P.sort()
time = 0

for i in range(len(P)):
    time += P[i] * (N-i)

print(time)
