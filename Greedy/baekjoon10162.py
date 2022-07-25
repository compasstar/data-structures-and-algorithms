"""
백준 10162번 전자레인지

1. 아이디어
- 그리디 알고리즘으로 5분 -> 1분 -> 10초 순서로 T애서 뺴나간다

2. 시간복잡도
- O(1)

3. 자료구조
- cnt, time
- T

"""
import sys
input = sys.stdin.readline

T = int(input())
cnt = [0, 0, 0]
time = [300, 60, 10]

if T % 10 != 0:
    print(-1)
else:
    for i in range(3):
        cnt[i] += T // time[i]
        T = T % time[i]

    print(" ".join(map(str, cnt)))