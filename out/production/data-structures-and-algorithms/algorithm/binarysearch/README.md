# Binary-Search


### import bisect
```
import bisect
left = bisect.bisect_left(list, value)
right = bisect.bisect_right(list, value)
```
- left: list에서 value값의 인덱스 중 가장 왼쪽 인덱스를 구한다
- right: list에서 value값의 인덱스 중 가장 오른쪽 인덱스를 구한다
- 한 list에 동일한 value가 많을 때 유용한 함수
- 예시) `list = [1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5]`


```
# start와 end의 범위는 항상 정답의 범위를 나타낼 수 있도록 해야 한다.
# start는 범위 -1, end는 범위 + 1 이다.
start = low - 1
end = high + 1

# start 와 end 사이에 mid가 존재해야 한다
while (start + 1 < end):
  mid = (start + end) // 2
  if Check(mid):
    start = mid
  else:
    end = mid
  print(end)
```
