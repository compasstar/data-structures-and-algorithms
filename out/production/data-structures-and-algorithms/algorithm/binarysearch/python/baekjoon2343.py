"""
1. 아이디어
- 블루레이 크기 중 최소 구하기
- 블루레이 크기: 강의 중 가장 긴 강의 ~ 모든 강의 합
- 너무 잘게 자르면 M보다 커지고
- 너무 크게 자르면 M보다 작아진다
- 블루레이 크기를 이분탐색을 통해 조정하며 알맞은 값의 범위 중에 정답을 구해보자


2. 시간복잡도
- 강의의 개수 N = 100,000
- 블루레이의 개수 M = 100,000
- log(N)

3. 자료구조

"""
import sys
input = sys.stdin.readline

N, M = map(int, input().split())
lectures = list(map(int, input().split()))

start = max(lectures) # 9
end = sum(lectures) # 45

while (start <= end):
    mid = (start + end) // 2

    bluelay_count = 1
    temp_sum = 0
    for lecture in lectures:
        if (temp_sum + lecture) > mid:
            bluelay_count += 1
            temp_sum = 0
        temp_sum += lecture

    if bluelay_count <= M:
        min_time = mid
        end = mid - 1
    elif bluelay_count > M:
        start = mid + 1

print(min_time)
