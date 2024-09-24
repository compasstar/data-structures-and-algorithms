# Queue (큐)
선입선출(First In Last Out) 구조로 저장하는 자료구조

```java
Queue<Integer> queue = new LinkedList<>();
```

## 메서드
| 메서드                | 설명                                                             | 시간복잡도 |
|:-------------------|:---------------------------------------------------------------|:-----:|
| boolean add(E e)   | rear 에 e 를 추가한다. (성공하면 true 반환, 실패하면 IllegalStateException 발생) | O(1)  |
| boolean offer(E e) | rear 에 e 를 추가한다. (성공하면 true 반환, 실패하면 false 반환)                 | O(1)  |
| E remove()         | head 의 노드를 제거하고 반환한다. (만약 empty 라면 NoSuchElementException 발생)  | O(1)  |
| E poll()           | head 의 노드를 제거하고 반환한다. (만약 empty 라면 null 반환)                    | O(1)  |
| E element()        | head 의 노드를 반환한다. (만약 empty 라면 NoSuchElementException 발생)       | O(1)  |
| E peek()           | head 의 노드를 반환한다. (만약 empty 라면 null 반환)                         | O(1)  |

## 특징
- rear(back) 에서 추가하고 head(front) 에서 제거한다 (FILO)
- Exception 을 발생시킬지 여부를 결정할 수 있다