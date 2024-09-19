"""
백준 1149번

1. 아이디어

- 결과부터 놓고 거꾸로 돌아간다
- 마지막이 빨강으로 끝나는 경우, 초록으로 끝나는 경우, 파랑으로 끝나는 경우
- 그 앞의 min 값으로 0번 인덱스까지 더해간다

2. 시간복잡도
- O(N)

3. 자료구조


"""
import sys
input = sys.stdin.readline

N = int(input())

rgb = [list(map(int, input().split())) for _ in range(N)]

for i in range(1, N):
    rgb[i][0] = min(rgb[i-1][1], rgb[i-1][2]) + rgb[i][0]
    rgb[i][1] = min(rgb[i - 1][0], rgb[i - 1][2]) + rgb[i][1]
    rgb[i][2] = min(rgb[i - 1][0], rgb[i - 1][1]) + rgb[i][2]

print(min(rgb[N-1][0], rgb[N-1][1], rgb[N-1][2]))

