"""
백준 5585번 거스름돈

1. 아이디어
- 가장 큰 돈부터 시작하여 돈을 빼가면서 잔돈 개수를 더해간다

2. 시간복잡도
- O(1)

3. 자료구조
- money

"""
import sys
input = sys.stdin.readline

money = 1000 - int(input())
cnt = 0

trace = [500, 100, 50, 10, 5, 1]

for t in trace:
    cnt += money // t
    money = money % t

print(cnt)