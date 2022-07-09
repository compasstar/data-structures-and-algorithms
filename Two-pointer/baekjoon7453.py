"""
백준 7453번 합이 0인 네 정수

1. 아이디어
- A,B 를 합치고, C,D를 합친다. -> N = 8000
- 정렬하고, AB의 첫 점과 CD의 끝 점을 잡고 투포인터를 돌린다

2. 시간복잡도
- 8000짜리를 두 번 돌기에 16000

3. 자료구조
- A,B,C,D
- AB, CD
- cnt
"""
import sys
input = sys.stdin.readline

N = int(input())
cnt = 0

nums = [list(map(int, input().split())) for _ in range(N)]

AB, CD = [], []
for i in range(N):
    for j in range(N):
        AB.append(nums[i][0] + nums[j][1])
        CD.append(nums[i][2] + nums[j][3])

AB.sort()
CD.sort()


small, big = 0, len(CD) - 1

while small < len(AB) and big >= 0:

    if AB[small] + CD[big] == 0:
        next_small, next_big = small + 1, big - 1

        while next_small < len(AB) and AB[next_small] == AB[small]:
            next_small += 1
        while next_big >= 0 and CD[next_big] == CD[big]:
            next_big -= 1

        cnt += (next_small - small) * (big - next_big)

        small = next_small
        big = next_big


    elif AB[small] + CD[big] < 0:
        small += 1
    else:
        big -= 1

print(cnt)

