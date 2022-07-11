"""
백준 2805번 나무 자르기

1. 아이디어
- 나무 높이 들에서 전부 H를 뺀다, 음수라면 0으로 만든다
- 값을 전부 더한 값이 M 이상이 되도록 한다

- 이진탐색을 통해 H를 설정하여 값을 찾는다

2. 시간복잡도
- 

3. 자료구조
- tree
- def binary_search

"""
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
tree = list(map(int, input().split()))

def binary_search(st, en):

    if st == en:
        print(st-1)
        return

    mid = (st + en) // 2

    sum_tree = 0
    for t in tree:
        if t >= mid:
            sum_tree += t - mid

    if sum_tree >= M:
        binary_search(mid+1, en)
    else:
        binary_search(st, mid)

max_height = max(tree)
binary_search(0, max_height)

