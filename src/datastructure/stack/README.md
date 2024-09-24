# Stack (스택)
후입선출(Last In First Out) 구조로 저장하는 자료구조

```java
Stack<Integer> stack = new Stack<>();
```

## 메서드
| 메서드             | 설명                    | 시간복잡도 |
|:----------------|:----------------------|:-----:|
| E peek()        | 스택의 top 을 반환한다        | O(1)  |
| E pop()         | 스택의 top 을 제거하고 반환한다   | O(1)  |
| E push(E item)  | 스택의 top 에 item 을 추가한다 | O(1)  |
| boolean empty() | 스택이 비어있는지 반환한다        | O(1)  |

## 특징
- 모든 메서드가 top 을 기준으로 한다
- top 에서만 작업을 하기에 속도가 빠르다
- top 에서만 작업을 하는 알고리즘에 적합하다 (LIFO)
