"""
백준 1072번 게임

1. 아이디어
- '더 해야하는 게임 횟수'를 이분탐색으로 찾는다

2. 시간복잡도
-

3. 자료구조


"""
import sys
input = sys.stdin.readline

X, Y = map(int, input().split())
Z = int(Y*100/X)

st = 1
en = X

def binary_search(st, en):

    while st <= en:

        mid = (st + en) // 2

        new_Z = int((Y+mid)*100/(X+mid))

        if new_Z <= Z:
            st = mid + 1
        else:
            en = mid - 1


    print(en + 1)


if Z >= 99:
    print(-1)
else:
    binary_search(st, en)