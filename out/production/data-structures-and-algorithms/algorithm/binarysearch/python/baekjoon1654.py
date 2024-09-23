"""
백준 1654 랜선 자르기

1. 아이디어
- 랜선의 길이를 조절해가면서 N개 보다 많이 만드는 최댓값을 구한다
- 랜선의 길이 0<=L<=min(랜선리스트)

- 이진탐색으로 길이를 조절한다

2. 시간복잡도
- O(KlgL)

3. 자료구조
- 랜선리스트 online

"""
import sys
input = sys.stdin.readline

K, N = map(int, input().split())
online = []
for k in range(K):
    online.append(int(input()))


def binary_search(st, en):

    if st > en:
        print(en) # st ~ en 이 랜선길이의 범위이고, en은 최댓값이다.
        return

    mid = (st+en) // 2

    cnt = 0
    for i in online:
        cnt += i//mid

    if cnt >= N:
        binary_search(mid+1, en)
    else:
        binary_search(st, mid-1)



max_length = max(online)
binary_search(1, max_length)