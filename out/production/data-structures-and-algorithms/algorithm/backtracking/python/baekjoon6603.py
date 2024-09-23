"""
백준 6603번

1. 아이디어
- 받은 리스트를 가지고 백트래킹을 통해 경우의 수를 전부 구하여 출력한다

2. 시간복잡도
- O(N!)

3. 자료구조


"""
import sys
input = sys.stdin.readline


def recur(num):
    if num == 6:
        print(" ".join(map(str, numbers)))
        return

    for i in S:
        if len(numbers) == 0 or i > numbers[-1]:
            numbers.append(i)
            recur(num + 1)
            numbers.pop()


while(True):

    S = list(map(int, input().split()))
    k = S.pop(0)
    if k == 0:
        break
    numbers = []

    recur(0)
    print()