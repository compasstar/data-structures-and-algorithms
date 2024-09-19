"""
백준 14891 톱니바퀴

1. 아이디어
- 톱니바퀴 4개를 만든다
- 톱니를 돌려서 다른 극이라면 반대 방향으로 회전한다

2. 시간복잡도

3. 자료구조
- 톱니바퀴: sawtooth = int[4][8]
- 명령: cmd = int[K][2]
"""
import sys
input = sys.stdin.readline

# 인덱스0: 12시, 인덱스2: 3시, 인덱스6: 9시
sawtooth = [list(map(int, input().strip())) for _ in range(4)]
K = int(input())
cmd = [list(map(int, input().split())) for _ in range(K)]

def rotate_sawtooth(index, dir):

    if dir == 1:
        sawtooth[index].insert(0, 0)
        last = sawtooth[index].pop()
        sawtooth[index][0] = last
    else:
        sawtooth[index].append(0)
        first = sawtooth[index].pop(0)
        sawtooth[index][-1] = first




for k in range(K):
    select = []

    index, dir = cmd.pop(0)
    index -= 1
    temp_index = index
    temp_dir = dir
    select.append([index, dir])

    # 해당 톱니바퀴 왼쪽
    while True:
        if index <= 0:
            break

        index -= 1
        dir *= -1

        if sawtooth[index+1][6] != sawtooth[index][2]:
            select.append([index, dir])
        else:
            break

    # 해당 톱나바퀴 오른쪽
    index = temp_index
    dir = temp_dir
    while True:
        if index >= 3:
            break

        index += 1
        dir *= -1

        if sawtooth[index-1][2] != sawtooth[index][6]:
            select.append([index, dir])
        else:
            break


    for i, d in select:
        rotate_sawtooth(i, d)


point = sawtooth[0][0] + sawtooth[1][0]*2 + sawtooth[2][0]*4 + sawtooth[3][0]*8
print(point)
