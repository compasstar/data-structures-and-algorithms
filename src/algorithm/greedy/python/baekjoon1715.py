"""
1. 아이디어
- 작은 것 부터 비교를 하면 되지 않을까?
- 힙을 사용하여 가장 작은 두개를 빼고 추가하고 반복한다

2. 시간복잡도
- 숫자묶음개수 N = 100000
- 각 숫자묶음 1000개
- O(NlogN + N) 정렬하고 찾기

3. 자료구조
- heapq 
- 힙은 알아서 정렬됨을 기억하자!
"""
import sys
import heapq
input = sys.stdin.readline

N = int(input())
cards = []
for _ in range(N):
    heapq.heappush(cards, int(input()))

cnt = 0
for i in range(N-1):
    min_1 = heapq.heappop(cards)
    min_2 = heapq.heappop(cards)
    heapq.heappush(cards, min_1 + min_2)
    cnt += min_1 + min_2

print(cnt)
