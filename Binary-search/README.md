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
