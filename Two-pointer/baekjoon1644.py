"""
백준 1644 소수의 연속합

1. 아이디어
- N보다 작은 소수의 리스트를 만든다
- 리스트를 투포인터로 돌면서 경우의 수를 센더ㅏ

2. 시간복잡도
- O(N)

3. 자료구조
- 소수의 리스트 prime_list = int[]
- 소수의 합 sum_prime
- 경우의 수 cnt
"""
import sys
import math
input = sys.stdin.readline

N = int(input())
# 에라토스테네스 체 소수 찾기
prime_list = [True for _ in range(N+1)]
for i in range(2, int(math.sqrt(N))+1):
    if prime_list[i] == True:
        j = 2
        while i*j <= N:
            prime_list[i*j] = False
            j += 1

new_prime_list = [x for x in range(2, N+1) if prime_list[x]==True]

sum_prime = 0
sum_prime_list = []
cnt = 0

# 실행
for i in new_prime_list:
    sum_prime += i
    sum_prime_list.append(i)

    while sum_prime > N:
        sum_prime -= sum_prime_list.pop(0)

    if sum_prime == N:
        cnt += 1


print(cnt)