"""
백준 1789번 수들의 합

1. 아이디어
- 작은 수들로 다 채우고, 마지막 마무리 숫자를 조절하여 S로 맞춘다
- 즉, 개수를 따질 때에는 1부터 큰 숫자로 더해가며 S보다 작게만 유지하면 된다
- 1부터 시작하여 숫자를 키워가며 더해간다. S 이상이 된다면 stop

2. 시간복잡도
- O(N)

3. 자료구조
- S
- total --> S가 되면 stop
- cnt, num

"""
import sys
input = sys.stdin.readline

S = int(input())
total = 0
cnt = 0
num = 1

while total <= S:
    total += num
    num += 1
    cnt += 1

cnt -= 1
print(cnt)
