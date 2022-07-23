"""
백준 1931번 회의실 배정

1. 아이디어
- 끝나는 시간 순으로 정렬한다
- 제일 먼저 끝나는 회의 1번
- 1번 회의 끝나고 그다음 가장 발리 끝나는 회의 2번
- 쭉쭉쭉 반복한다

2. 시간복잡도
- 정렬 NlogN
- 회의 넘어가면서 검사 N

3. 자료구조
- 회의 meeting = int[N]
- cnt

"""
import sys
input = sys.stdin.readline

N = int(input())
meeting = [ list(map(int, input().split())) for _ in range(N) ]
meeting.sort()
meeting.sort(key=lambda x:x[1])
cnt = 0


end_meeting = meeting[0][1]
cnt += 1

for i in range(1, N):
    if meeting[i][0] >= end_meeting:
        cnt += 1
        end_meeting = meeting[i][1]

print(cnt)