"""
1. 아이디어
- 매번 도시를 넘어갈때마다 기름을 넣는데
- 기름값이 더 비싸면 전 도시 비용으로 계산하고
- 기름값이 더 싸면 지금 도시 비용으로 계산한다

2. 시간복잡도
- N = 100000
- 거리 = 1,000,000,000 자연수
- 리터가격 = 1,000,000,000 자연수
- O(N)

3. 자료구조
"""
import sys
input = sys.stdin.readline

N = int(input()) # 도시 개수
distance = list(map(int, input().split())) # [2, 3, 1]
price = list(map(int, input().split())) # [5, 2, 4, 1]
price = price[:-1]

min_price = price[0]
temp_price = 0
for i in range(N-1):
    min_price = min(min_price, price[i])
    temp_price += min_price * distance[i]
print(temp_price)
