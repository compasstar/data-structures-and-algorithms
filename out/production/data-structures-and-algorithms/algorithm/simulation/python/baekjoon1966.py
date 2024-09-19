"""
백준 1966번

1. 아이디어
- 큐를 만든다
- 리스트 내부의 중요도를 비교하여 0번 인덱스보다 높은 것이 있다면 맨 뒤로 보낸다
- 중요도가 가장 높다면 pop한다

- 만일 고유 인덱스가 M과 동일하다면 여태 pop한 것들의 개수를 더하여 출력한다

2. 시간복잡도
- O(N^2) = 10000 < 2억

3. 자료구조
- queue = [[고유 인덱스, 중요도], [], ...]

"""
import sys
input = sys.stdin.readline


T = int(input())
for _ in range(T):



    N, M = map(int, input().split())
    input_list = list(map(int, input().split()))
    q = []
    for i in range(len(input_list)):
        q.append([i, input_list[i]])
    cnt = 0


    while True:

        sw = False
        pres = q.pop(0)

        for i in range(len(q)):
            if pres[1] < q[i][1]:
                q.append(pres)
                sw = True
                break

        if sw == False:
            cnt += 1
            if pres[0] == M:
                break


    print(cnt)