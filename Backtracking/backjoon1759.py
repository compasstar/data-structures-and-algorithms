"""
백준 1759번

1. 아이디어
- 백트래킹을 통해 모든 경우의 수 출력

2. 시간복잡도
- O(N!)

3. 자료구조

"""
import sys
input = sys.stdin.readline

L,C = map(int, input().split())
strings = list(input().split())
result = []
answer = []
vowel = ['a', 'e', 'i', 'o', 'u']
consonent = ['b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z']

def recur(num):

    if num == L:
        vowel_count = 0
        consonent_count = 0

        for i in result:
            if i in vowel:
                vowel_count += 1
            if i in consonent:
                consonent_count += 1

        if vowel_count >= 1 and consonent_count >= 2:
            answer.append("".join(result))
        return

    for i in strings:
        if len(result)==0 or ord(result[-1]) < ord(i):
            result.append(i)
            recur(num+1)
            result.pop()

recur(0)
answer.sort()
for i in answer:
    print(i)