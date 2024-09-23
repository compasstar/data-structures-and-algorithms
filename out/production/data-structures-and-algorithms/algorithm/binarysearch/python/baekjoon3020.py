
import sys
input = sys.stdin.readline

N, H = map(int, input().split())
hedges_down = []
hedges_up = []
for i in range(N):
    if i % 2 == 0:
        hedges_down.append(int(input()))
    else:
        hedges_up.append(int(input()))

hedges_down.sort()
hedges_up.sort()

hedges_height = []

for h in range(1, H+1):
    start = -1
    end = int(N/2)
    while(start + 1 < end):
        mid = (start + end) // 2
        if (h > hedges_down[mid]):
            start = mid
        else:
            end = mid
    check_down = end

    start = -1
    end = int(N/2)
    while(start + 1 < end):
        mid = (start + end) // 2
        if (h < H - hedges_up[mid] + 1):
            start = mid
        else:
            end = mid
    check_up = end
    hedges_height.append(int(N/2) - check_down + int(N/2) - check_up)
    
min_value = min(hedges_height)
count_min_value = hedges_height.count(min_value)
print(min_value, count_min_value)

