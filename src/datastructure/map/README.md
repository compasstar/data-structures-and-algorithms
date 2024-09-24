# HashMap (해시맵)
Key - Value 값을 통해 데이터를 저장하는 자료구조. Key 값을 기준으로 Value 값을 찾을 수 있다.

```java
Map<Integer, String> hashMap = new HashMap<Integer, String>();
```

## 메서드
| 메서드                    | 설명                                          | 시간복잡도 |
|:-----------------------|:--------------------------------------------|:-----:|
| V get(Object key)      | key 값을 통해 value 값을 탐색한다                     | O(1)  |
| V put(K key, V value)  | key-value 쌍을 추가한다. (key 값에 매핑된 이전 value 리턴) | O(1)  |
| V remove(Object key)   | 해당 key-value 쌍 제거                           | O(1)  |
| Collection<V> values() | 해시맵의 values 반환                              |       |

## 특징
- 탐색, 추가, 제거 모두 시간복잡도 O(1) 로 매우 빠르다

# TreeMap (트리맵)
Key - Value 쌍을 RedBlack-Tree 로 관리하는 자료구조

```java
TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
```

## 메서드
| 메서드                              | 설명                                          |  시간복잡도   |
|:---------------------------------|:--------------------------------------------|:--------:|
| V get(Object key)                | key 값을 통해 value 값을 탐색한다                     | O(log n) |
| V put(K key, V value)            | key-value 쌍을 추가한다 (key 값에 매핑된 이전 value 리턴) | O(log n) |
| V remove(Object key)             | 해당 key-value 쌍 제거                           | O(log n) |
| Collection<V> values()           | 트리맵의 values 반환                              |          |
| SortedMap<K, V> headMap(K toKey) | toKey **미만**의 key 값만을 가지는 트리맵의 부분을 반환           |          |
| SortedMap<K, V> tailMap(K fromKey) | fromKey **이상**의 key 값만을 가지는 트리맵의 부분을 반환           |          |
| SortedMap<K, V> subMap(K fromKey, K toKey) | fromKey **이상** toKey **미만**의 key 값만을 가지는 트리맵의 부분을 반환           |          |
| K lowerKey(K key) | 해당 key 보다 바로 더 작은 key 값을 반환한다           | O(log n) |
| K higherKey(K key) | 해당 key 보다 바로 더 큰 key 값을 반환한다           | O(log n) |
| K firstKey() | 가장 작은 key 값을 반환한다           | O(log n) |
| K lastKey() | 가장 큰 key 값을 반환한다           | O(log n) |

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

TreeMap<Node, Integer> treeMap = new TreeMap<>();
```

### 람다식
```java
TreeMap<Node, Integer> treeMap = new TreeMap<>((n1, n2) -> n1.w - n2.w);
```

w를 기준으로 우선순위 정하기. w가 낮을수록 우선순위가 높습니다.


## 특징
- 탐색, 추가, 제거에 O(log n)의 시간복잡도를 갖기에 해시맵에 비해 불리하다
- RedBlack-Tree 구조를 사용하기에 정렬이 가능하다
- key 값을 기준으로 더 작거나 더 큰 key 값들을 탐색하는 게 가능하다
- 가장 작은 key 값과 가장 큰 key 값을 구할 수 있다
