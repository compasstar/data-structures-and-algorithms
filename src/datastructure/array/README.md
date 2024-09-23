# Array (배열)

<img src="https://upload.wikimedia.org/wikipedia/commons/3/3f/Array1.svg" style="background-color: white"/>

번호(인덱스)에 각 데이터를 대응시킨 자료구조

## 코드
```java
//선언
int[] array = new int[10];

//추가
array[4] = 5;

//접근
int a = array[4];

//제거
array[4] = 0;

```

## 시간복잡도
|  접근  |  추가  | 제거 |
|:----:|:----:| :---: |
| O(1) | O(1) | O(1) |
인덱스를 통해 접근하기 때문에 접근, 추가, 제거 모두 시간복잡도가 O(1)입니다.

## 특징
- 크기가 고정되어 있습니다
- 접근, 추가, 제거 시간복잡도가 O(1)으로 빠릅니다
