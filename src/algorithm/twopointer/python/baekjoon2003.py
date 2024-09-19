"""
백준 2003번 수들의 합2

1. 아이디어
- 투포인터로 합이 M인 경우의 수를 구한다
- M이라면 경우의 수를 하나 더하고 앞의 인덱스를 하나 뺀다
- 숫자들의 합이 만일 M보다 작다면 더하고, 더 크다면 앞의 인덱스를 크지 않을 때까지 때까지 뺀다

2. 시간복잡도
- O(N)

3. 자료구조
- numbers
- 합 sum_numbers
- 경우의 수 cnt
"""
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
A = list(map(int, input().split()))
sum_numbers = []
cnt = 0

while A:

    if sum(sum_numbers) == M:
        cnt += 1
        sum_numbers.pop(0)
        a = A.pop(0)
        sum_numbers.append(a)
    elif sum(sum_numbers) < M:
        a = A.pop(0)
        sum_numbers.append(a)
    else:
        while sum(sum_numbers) > M:
            sum_numbers.pop(0)

while sum_numbers:
    if sum(sum_numbers) == M:
        cnt += 1
    sum_numbers.pop(0)


print(cnt)