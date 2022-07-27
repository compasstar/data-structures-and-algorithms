"""
백준 11726번 2nx 타일링

1. 아이디어
- 점화식 An = An-1 + An-2
- N값 구하기 위해 for 문으로 3부터 N까지의 값을 구해주기
- 이전값, 이전이전값을 더해서, 100007로 나눠저장

2. 시간복잡도
- O(N)

3. 자료구조
- DP값 저장하는 (경우의 수) 배열: int[]
    - 최댓값: 10007보다 작음

"""
import sys
input = sys.stdin.readline

N = int(input())
rs = [0, 1, 2]

for i in range(3, N+1):
    rs.append(rs[i-2] + rs[i-1])

answer = rs[N] % 10007
print(answer)

