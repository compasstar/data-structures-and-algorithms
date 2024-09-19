"""
백준 2839번 설탕 배달

1. 아이디어
- 3으로 나누어지는 수가 나올 때마다 체크해가며 N을 5단위로 계속해서 뺴간다
- 3으로 나누어지는 가장 작은 N을 구하고, 이를 3으로 나눈 몫을 카운트한다
- 5씩 뺀 카운트와, 3으로 나눈 몫의 카운트를 합하여 출력한다

- 만일 아무리 5씩 빼도 3으로 나누어지지 않는다면 -1을 출력한다


2. 시간복잡도
- O(N)

3. 자료구조


"""
import sys
input = sys.stdin.readline

N = int(input())
stock_five = 0

true_N = -1
true_stock_five = -1

while N >= 0:

    if N % 3 == 0:
        true_N = N
        true_stock_five = stock_five

    N -= 5
    stock_five += 1


if true_N == -1:
    print(-1)
else:
    stock_three = true_N // 3
    print(true_stock_five + stock_three)


