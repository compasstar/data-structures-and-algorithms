"""
백준 1806번 부분합

1. 아이디어
- 투포인터로 수열을 돌면서 합이 S 이상이라면 그것의 길이를 구한다
- S 이상인 부분합을 구할 때마다 min처리를 해서 최솟값을 구한다

2. 시간복잡도
- O(N)

3. 자료구조
- numbers = int[N]
- 부분합최소길이 min_length
- 합이 S 인지 구하기 위한 리스트 sl
"""
import sys
input = sys.stdin.readline

N, S = map(int, input().split())
numbers = list(map(int, input().split()))
sl = []
sum_sl = 0
min_length = N+1


for n in numbers:
    sl.append(n)
    sum_sl += n

    while sum_sl >= S:
        min_length = min(min_length, len(sl))
        sum_sl -= sl.pop(0)


if min_length == N+1:
    print(0)
else:
    print(min_length)

