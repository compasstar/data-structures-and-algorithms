"""
백준 2217번 로프

1. 아이디어
- 로프를 내림차순으로 정렬한다
- 사용 하는 로프 개수를 하나씩 늘려가며 최대 중량을 업데이트해간다

2. 시간복잡도
- O(N)

3. 자료구조
- lope
- min_value

"""
import sys
input = sys.stdin.readline

N = int(input())
lope = [int(input()) for _ in range(N)]

lope.sort()
lope.reverse()
max_value = 0

stock = 0
for l in lope:
    stock += 1
    max_value = max(max_value, l * stock)

print(max_value)

