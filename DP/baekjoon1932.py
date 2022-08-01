"""
백준 1932번 정수 삼각형

1. 아이디어
- DP 역순으로 내려온다
- 끝에 숫자부터 자신과 같은 인덱스 또는 하나 작은 인덱스 중에 더 큰 값으로 더해간다

2. 시간복잡도
- O(500^2)

3. 자료구조
- 삼각형: tri


"""
import sys
input = sys.stdin.readline

N = int(input())
tri = [list(map(int, input().split())) for _ in range(N)]

for i in range(1, N):
    for j in range(len(tri[i])):

        if j == 0:
            tri[i][j] += tri[i-1][j]
        elif j == len(tri[i])-1:
            tri[i][j] += tri[i-1][j-1]
        else:
            tri[i][j] += max(tri[i-1][j-1], tri[i-1][j])

max_value = 0
for i in range(N):
    max_value = max(max_value, tri[N-1][i])

print(max_value)
