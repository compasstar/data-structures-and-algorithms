"""
백준 1541번 잃어버린 괄호

1. 아이디어
- 마이너스 부호가 있다면 ( 를 하고, 다음 - 가 나오면 이 앞에 )를 붙여 계산한다

2. 시간복잡도
- O(N)

3. 자료구조
- 식: nums

"""
import sys
input = sys.stdin.readline

l = input()

print(l.split('-'))

sign = []
nums = []

num = ''
for i in l:

    if i == '-' or i == '+':
        sign.append(i)

        while num[0] == '0':
            num = num[1:]

        nums.append(num)
        num = ''
    else:
        num += i
while num[0] == '0':
     num = num[1:]
nums.append(num)

nums = list(map(int, nums))


# 실행

min_value = nums[0]
sw = 0

for i in range(len(sign)):

    if sign[i] == '-':
        sw = 1

    if sw == 0:
        min_value += nums[i+1]
    else:
        min_value -= nums[i+1]


print(min_value)





