"""
백준 14499번

1. 아이디어
- 주사위가 지도 위를 구르며 주사위의 윗면을 출력한다
- 주사위가 이동했을 때, 지도가 0이면 주사위의 바닥면 숫자 복사
                  , 지도가 0이 아니면 지도 위의 숫자를 주사위의 바닥면에 복사 후 칸은 0

2. 시간복잡도
- O(K)

3. 자료구조
- 지도: region = int[][]
- 주사위 전개도: dice = int[6] = [밑면, 동, 서, 북, 남, 윗면]
- 명령: cmd = int[K]
- 구르기 함수: def rolling_dice(dice, dir)
"""

import sys
input = sys.stdin.readline

# 여기 체크해봐 y, x 맞는지
N, M, y, x, K = map(int, input().split())

region = [list(map(int, input().split())) for _ in range(N)]
cmd = list(map(int, input().split()))
dice = [0, 0, 0, 0, 0, 0]


def rolling_dice(dice, dir):
    # 주사위 전개도: dice = int[6] = [밑면, 동, 서, 북, 남, 윗면]
    if dir == 1: # 1. 주사위를 동쪽으로 굴렸을 때
        new_dice = [dice[1], dice[5], dice[0], dice[3], dice[4], dice[2]]
    elif dir == 2: # 2. 주사위를 서쪽으로 굴렸을 때
        new_dice = [dice[2], dice[0], dice[5], dice[3], dice[4], dice[1]]
    elif dir == 3: # 3. 주사위를 북쪽으로 굴렸을 때
        new_dice = [dice[3], dice[1], dice[2], dice[5], dice[0], dice[4]]
    else: # 4. 주사위를 남쪽으로 굴렸을 때
        new_dice = [dice[4], dice[1], dice[2], dice[0], dice[5], dice[3]]

    return new_dice

dy = [0, 0, 0, -1, 1]
dx = [0, 1, -1, 0, 0]
for k in cmd:
    py = y + dy[k]
    px = x + dx[k]

    if py < 0 or py >= N or px < 0 or px >= M:
        continue

    # 1. 주사위를 굴린다
    dice = rolling_dice(dice, k)
    y, x = py, px

    # 2. 이동한 칸에 쓰여있는 수가 0이면, 주사위 바닥면에 쓰여있는 수 복사
    if region[y][x] == 0:
        region[y][x] = dice[0]
    else: # 0이 아닌 경우에는 칸에 쓰여있는 수가 주사위 바닥면으로 복사, 칸은 0
        dice[0] = region[y][x]
        region[y][x] = 0

    print(dice[5])




