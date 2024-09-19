"""
1. 아이디어
- BFS 로 1에서 주위 몇 개가 연결되어 있는지 센다
- 네트워크를 구현해야 한다 {(1,2), (2,3), (1,5), (5,2), (5,6), (4,7)}
- 1번을 pop 하고 연결되어 있는 것을 기준으로 다시 반복한다
- pop0. {(1,2), (2,3), (1,5), (5,2), (5,6), (4,7)}. queue(1)
- pop1. {(2,3), (5,2), (5,6), (4,7)}. queue(2, 5)
- pop2. {(5,6), (4,7)} - queue(5, 3)
- pop3. {(4, 7)} - queue(3)
- chk 1번부터 N번까지 연결되었다고 나오면 True
-

2. 시간복잡도
- O(V + E)
- V = N <= 100
- E = NC2 <= 100*99/2

3. 자료구조
- queue
- 컴퓨터 네트워크 리스트
- chk

"""

import sys
input = sys.stdin.readline

n = int(input()) # 컴퓨터 개수

edge = int(input())

network = []

for _ in range(edge):
    l = list(map(int, input().split()))
    network.append(l)

chk = [False] * (n+1)

queue = [1]


def bfs(num):

    del_list = []

    for i in range(len(network)):
        first = network[i][0]
        second = network[i][1]

        if (first == num):
            if chk[second] == False:
                queue.insert(0, second)
                chk[second] = True
            del_list.append(i)

        elif (second == num):
            if chk[first] == False:
                queue.insert(0, first)
                chk[first] = True
            del_list.append(i)

    for i in del_list[::-1]:
        del network[i]




while(queue):
    num = queue.pop()
    bfs(num)


chk[1] = False

print(chk.count(True))
