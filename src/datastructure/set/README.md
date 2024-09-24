# HashSet (해시셋)
중복을 허용하지 않으며 순서가 없는 자료구조

```java
HashSet<String> set = new HashSet<>();
```

## 메서드
| 메서드                        | 설명                                                       | 시간복잡도 |
|:---------------------------|:---------------------------------------------------------|:-----:|
| boolean add(E e)           | e 를 추가한다. (성공하면 true, 이미 존재하면 추가하지 않고 false 반환)          | O(1)  |
| boolean contains(Object o) | o를 지니고 있는지 반환한다.                                         | O(1)  |
| boolean remove(Object o)   | o를 제거한다. (없다면 false 반환)                                  | O(1)  |

## 특징
- Iterator 사용이 가능하다 (순회할 때 사용)
- HashSet 의 경우 내부적으로 HashMap 을 생성하는 방식으로 작동하기에 성능 차이는 없다


# TreeSet (트리셋)
Red-Black Tree 형태로 데이터를 저장하는 자료구조. 데이터의 중복을 허용하지 않고 저장순서를 유지하지 않으나, 정렬이 가능하다.

```java
TreeSet<String> set = new TreeSet<>();
```

## 메서드
| 메서드                                             | 설명                                                       |  시간복잡도   |
|:------------------------------------------------|:---------------------------------------------------------|:--------:|
| boolean contains(Object o)                      | o가 존재하는지 반환                                              | O(log n) |
| boolean add(E e)                                | e 를 추가한다 (만약 이미 값이 존재한다면 false 반환)                       | O(log n) |
| boolean remove(Object o)                        | e 를 제거한다 (존재하지 않는다면 false 반환)                            | O(log n) |
| SortedSet<E> headSet(E toElement)               | toElement **미만**의 값만을 가지는 트리셋의 부분을 반환                    |          |
| SortedSet<E> tailSet(E fromElement)             | fromElement **이상**의 값만을 가지는 트리셋의 부분을 반환                  |          |
| SortedSet<E> subSet(K fromElement, E toElement) | fromElement **이상** toElement **미만**의 값만을 가지는 트리셋의 부분을 반환 |          |
| E lower(E e)                                    | e 보다 바로 더 작은 값을 반환한다                                     | O(log n) |
| E higher(E e)                                   | e 보다 바로 더 큰 값을 반환한다                                      | O(log n) |
| E first()                                       | 가장 작은 값을 반환한다                                         | O(log n) |
| E last()                                        | 가장 큰 값을 반환한다                                         | O(log n) |

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

TreeSet<Node> treeSet = new TreeSet<>();
```

### 람다식
```java
TreeSet<Node> treeSet = new TreeSet<>((n1, n2) -> n1.w - n2.w);
```

w를 기준으로 우선순위 정하기. w가 낮을수록 우선순위가 높습니다.

## 특징
- Iterator 사용이 가능하다 (순회할 때 사용)
- TreeSet 의 경우 내부적으로 TreeMap 을 생성하는 방식으로 작동하기에 성능 차이는 없다
