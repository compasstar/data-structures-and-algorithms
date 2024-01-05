"""
1. 아이디어
- 부 배열의 합을 더해서 T가 되는 모든 부 배열 쌍의 개수를 구하라
- 계산? 대입? ==> 

- 자연수가 아니라 정수라는 점 기억하기!


2. 시간복잡도
N, M = 1000

3. 자료구조


"""
import sys
import bisect
input = sys.stdin.readline

T = int(input())
N = int(input())
A = list(map(int, input().split()))
M = int(input())
B = list(map(int, input().split()))

def count_left(l, index):
    cnt = 0
    while True:
        if (index-1 >= 0 and l[index] == l[index-1]):
            cnt += 1
            index -= 1
        else:
            break
    return cnt

def count_right(l, index):
    cnt = 0
    while True:
        if (index+1 < len(l) and l[index] == l[index+1]):
            cnt += 1
            index += 1
        else:
            break
    return cnt


# 1. 부분집합 다 구한다
A_sub = []
B_sub = []

for i in range(N):
    a = A[i]
    A_sub.append(a)
    for j in range(i+1, N):
        a += A[j]
        A_sub.append(a)

for i in range(M):
    b = B[i]
    B_sub.append(b)
    for j in range(i+1, M):
        b += B[j]
        B_sub.append(b)
        
A_sub.sort()
B_sub.sort()

T_count = 0
for a in A_sub:
    left = bisect.bisect_left(B_sub, T - a)
    right = bisect.bisect_right(B_sub, T - a)
    T_count += (right - left)
print(T_count)
