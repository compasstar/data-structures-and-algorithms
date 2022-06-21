"""
백준 1182번

1. 아이디어
- 백트래킹으로 모든 부분집합을 전부 구한 뒤, 합이 S가 0이 되는 것을 카운트한다

2. 시간복잡도
- O(N!)

3. 자료구조

"""

import sys
input = sys.stdin.readline

N, S = map(int, input().split())
numbers = list(map(int, input().split()))

sum_number = 0
count = 0
index = -1

def recur(num, size):
    global sum_number, count, index

    if num == size:
        if sum_number == S:
            count += 1
        return

    for i in range(len(numbers)):
        if i > index:
            temp = index
            index = i
            sum_number += numbers[i]

            recur(num+1, size)

            index = temp
            sum_number -= numbers[i]

for size in range(1, N+1):
    recur(0, size)

print(count)