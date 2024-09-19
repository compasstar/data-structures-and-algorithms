"""
백준 2579번 계단 오르기

1. 아이디어
- [0, ...] 계단 개수만큼 리스트를 만들어놓고
- 그 계단에 도착했을 때의 최대 점수로 계속해서 초기화 시킨다
- 마지막 계단의 최대점수를 출력한다

2. 시간복잡도

3. 자료구조
- stair = int[N]
- point = int[]


"""
import sys
input = sys.stdin.readline

N = int(input())
stair = [0 for _ in range(300)]
for i in range(N):
    stair[i] = int(input())

point = [0 for _ in range(300)]

point[0] = stair[0]
point[1] = stair[0] + stair[1]
point[2] = max(stair[0] + stair[2], stair[1] + stair[2])

for i in range(3, N):
    point[i] = max(stair[i] + stair[i-1] + point[i-3], stair[i] + point[i-2])

print(point[N-1])