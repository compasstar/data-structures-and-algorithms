"""
백준 2473번 세 용액

1. 아이디어
- N = 5000 이라고 할 때, 3중 for문 사용 -> 5000^3 = 225,000,000,000 -> 초과
- 2중 for문 사용 -> 5000^2 = 25,000,000 < 2억 --> 가능

- 우선 정렬부터 한다
- 1차원 for문에서 수 하나를 고정시키고, 나머지 두 숫자를 투포인터로 돌면서 절댓값이 가장 작은 합을 찾는다
- 투포인터는 1차원 for문에서 잡은 수보다 큰 리스트에서, 가장 작은 값과 가장 큰값으로 각각 잡는다
- 세 수의 합이 0보다 작다면 왼쪽 포인터를 오른쪽으로 한 칸 옮긴다
- 세 수의 합이 0보다 크다면 오른쪽 포인터를 왼쪽으로 한 칸 옮긴다
- 만일 같다면 아무거나 상관없는데 여기서는 오른쪽 포인터를 옮기겠다
- 두 포인터가 만날 때까지 반복하고, 1차원 for문에서 다음 인덱스로 이동한다

2. 시간복잡도
- 5000^2 < 2억

3. 자료구조
- nums = int[N]
- value = int


"""
import sys


def main():
    input = sys.stdin.readline

    N = int(input())
    nums = list(map(int, input().split()))
    nums.sort()
    value = 3000000000
    answer = []

    for l in range(N):
        small = l+1
        big = N-1
        nl = nums[l]

        while small < big:
            ns = nums[small]
            nb = nums[big]

            sum_liquid = ns + nl + nb
            if value > abs(sum_liquid):
                value = abs(sum_liquid)
                answer = [ns, nl, nb]

            if sum_liquid < 0:
                small += 1
            else:
                big -= 1


    answer.sort()
    print(" ".join(map(str, answer)))

main()


