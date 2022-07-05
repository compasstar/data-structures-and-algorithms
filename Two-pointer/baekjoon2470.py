"""
백준 2470번 두 용액

1. 아이디어
- 만일 2중 for문으로 전부 돈다면 100억이므로 불가능 -> 투포인터 사용
[-99, -2, -1, 4, 98]
- 리스트를 정렬하고, 포인터를 양 끝에 잡는다 (최솟값, 최댓값)
- 두 포인터의 합이 0보다 작다면 왼쪽 포인터를 오른쪽으로 한칸 옮긴다
- 두 포인터의 합이 0보다 크다면 오른쪽 포인터를 왼쪽으로 한칸 옮긴다
- 두 포인터가 만난다면 종료한다

2. 시간복잡도
- O(N)
- 리스트를 한번 순회할 뿐이기 때문

3. 자료구조
- 용액 리스트: l = int[N]
- 특성값: value
"""

import sys
input = sys.stdin.readline

N = int(input())
l = list(map(int, input().split()))
l.sort()
value = 2000000000
answer = []

small = 0
big = len(l)-1
while small<big:

    mix = l[small] + l[big]
    if abs(mix) < value:
        value = abs(mix)
        answer = [l[small], l[big]]

    if mix < 0:
        small += 1
    elif mix > 0:
        big -= 1
    else:
        break

print(" ".join(map(str, answer)))