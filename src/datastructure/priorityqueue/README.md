# PriorityQueue (우선순위 큐)
각 노드가 우선순위를 가지고 있는 자료구조. 주로 Heap 구조로 구현된다.

```java
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
```

## 메서드
| 메서드                | 설명                                                       |  시간복잡도   |
|:-------------------|:---------------------------------------------------------|:--------:|
| boolean add(E e)   | e 를 추가한다.                                                | O(log n) |
| E poll()           | head 를 제거하고 반환한다. (만약 empty 라면 null 반환)                  | O(log n) |
| E peek()           | head 의 노드를 반환한다. (만약 empty 라면 null 반환)                   |   O(1)   |

## 우선순위 설정하기

### Comparable
```java
class Node implements Comparable<Node> {
    int id;
    int w;

    public Node(int id, int w) {
        this.id = id;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return this.w - o.w;
    }
}

PriorityQueue<Node> pq = new PriorityQueue<>();
```

### 람다식
```java
PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.w - n2.w);
```

w를 기준으로 우선순위 정하기. w가 낮을수록 우선순위가 높습니다. 

## 특징
- Iterator 사용 가능하지만, 우선순위대로 보여주지 않는다. 만약 우선순위대로 값을 확인하고 싶다면 `poll()`을 반복해서 호출해야 한다.