"""
백준 2110번 공유기 설치

1. 아이디어
- 집 좌표를 정렬한다
- 이진 탐색으로 최소거리를 조절하면서 모든 공유기를 설치하는 경우를 구한다
- 최소 거리가 가장 큰 것으로 저장한다

2. 시간복잡도
- NlogN

3. 자료구조
- house
- def binary_search

"""
import sys
input = sys.stdin.readline

N, C = map(int, input().split())
house = []
for _ in range(N):
    house.append(int(input()))
house.sort()


def binary_search():

    st, en = 1, house[-1] - house[0]

    while st <= en:

        mid = (st + en) // 2

        cnt = 1
        current = house[0]
        for i in range(1, N):
            if current + mid <= house[i]:
                cnt += 1
                current = house[i]

        if cnt >= C:
            global answer
            st = mid + 1
            answer = mid

        else:
            en = mid - 1



answer = 0
binary_search()
print(answer)
