"""
백준 1003 피보나치 함수

1. 아이디어

0 -> 1, 0
1 -> 0, 1
2 -> 1, 1
3 -> 1, 2
4 -> 2, 3
5 -> 3, 5
6 -> 5, 8

- 앞 순서 두 숫자의 0, 1 의 합을 구하면 된다 (DP)
- 0, 1 따로 따로 하자

2. 시간복잡도
- O(N)

3. 자료구조
- sum_0
- sum_1

"""
import sys
input = sys.stdin.readline


def main():

    N = int(input())

    sum_0 = [1, 0]
    sum_1 = [0, 1]

    for i in range(2, N+1):
        sum_0.append(sum_0[i-1] + sum_0[i-2])
        sum_1.append(sum_1[i - 1] + sum_1[i - 2])

    print(sum_0[N], sum_1[N])


T = int(input())
for _ in range(T):
    main()

