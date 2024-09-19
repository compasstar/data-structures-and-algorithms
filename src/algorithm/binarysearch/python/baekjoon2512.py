"""
백준 2512번 예산

1. 아이디어
- 우선 total_money 가 sum(plz) 이하인지 확인하고, 이하라면 그대로 배정
- 정수 상한액을 0 ~ max(plz) 으로 잡고 이분탐색을 통해 최대 금액을 찾아낸다

2. 시간복잡도
- N = e5
- 정렬: NlogN
- 이분탐색: logN

3. 자료구조
- plz
- def binary_search()

"""
import sys
input = sys.stdin.readline

N = int(input())
plz = list(map(int, input().split()))
total_money = int(input())
plz.sort()

def binary_search(st, en, target):

    while st <= en:

        mid = (st + en) // 2 # 상한액

        total = 0
        for p in plz:
            if p <= mid:
                total += p
            else:
                total += mid

        if total <= target:
            st = mid + 1
        else:
            en = mid - 1

    print(en)





if sum(plz) <= total_money:
    print(max(plz))

else:
    binary_search(0, max(plz), total_money)