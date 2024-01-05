"""
1. 아이디어
- '좋은 수'의 개수 구하기
- 계산 or 대입 ==> 계산
- 숫자리스트를 돌면서 각 숫자가 좋은 수 인지 검사한다
- 좋은 수 인지 검사하는 방법은 투포인터
- * 주의할점! A는 자연수가 아니라 정수이다!!!!!!!!

2. 시간복잡도
- N = 2000 
- 리스트길이 * 투포인터 (N * N)

3. 자료구조

"""
import sys
input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split()))
A.sort()

good = 0

for i in range(N):
    start_index = 0
    end_index = N - 1

    while(start_index < end_index):
        if (A[start_index] + A[end_index] < A[i]):
            start_index += 1
        elif (A[start_index] + A[end_index] > A[i]):
            end_index -= 1
        elif (A[start_index] + A[end_index] == A[i]):
            if (start_index == i):
                start_index += 1
            elif (end_index == i):
                end_index -= 1
            else:
                good += 1
                break

print(good)
    
