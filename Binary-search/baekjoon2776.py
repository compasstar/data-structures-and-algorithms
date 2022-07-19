"""
백준 2776번 암기왕

1. 아이디어
- 2중 for문으로 돌면 e12 이기 때문에 실패 -> 이분탐색으로 숫자를 찾는다
- 수첩 1을 정렬하고, 이분탐색한다

2. 시간복잡도
- e7loge7

3. 자료구조
- 수첩1 list_1
- 수첩2 list_2
- 이분탐색 def binary_search()

"""
import sys
input = sys.stdin.readline


def binary_search(st, en, target, l):

    while st < en:

        mid = (st + en) // 2

        if l[mid] < target:
            st = mid + 1
        else:
            en = mid

    return l[en]


def main():
    N = int(input())
    list_1 = list(map(int, input().split()))
    M = int(input())
    list_2 = list(map(int, input().split()))
    list_1.sort()


    for num in list_2:
        num1 = binary_search(0, len(list_1)-1, num, list_1)
        if num1 == num:
            print(1)
        else:
            print(0)



# 테스트케이스 실행
T = int(input())
for _ in range(T):
    main()