# Linked List (연결 리스트)
노드당 데이터와 포인터를 가지고 노드끼리 한 줄로 연결되어 있는 자료구조

![linkedlist](https://github.com/user-attachments/assets/4b8a0f54-e3be-40c6-995d-0166e9291ed3)

```java
LinkedList<Integer> linkedList = new LinkedList<>();
```

## 메서드
| 메서드                                           | 설명             | 시간복잡도 |
|:----------------------------------------------|:---------------|:-----:|
| void addFirst(Object object)                  | 맨 앞에 object 추가 | O(1)  |
| void addLast(Object object)                   | 맨 뒤에 object 추가 | O(1)  |
| boolean add(Object object)                    | 맨 뒤에 object 추가 | O(1)  |
| Object removeFirst()                          | 맨 앞 노드 삭제      | O(1)  |
| Object removeLast()                           | 맨 뒤 노드 삭제      | O(1)  |
| Object remove()                               | 첫번째 노드 삭제      | O(1)  |
| Object get(int index) | 해당 인덱스 노드 탐색   | O(N)  |

## 특징
- 크기가 가변적입니다
- 추가와 제거는 연결리스트의 끝부분에서 작업하기에 시간복잡도가 O(1) 입니다
- 인덱스가 없기에 검색에는 O(N)의 시간복잡도가 걸립니다
- 탐색의 시간을 단축하기 위해 ArrayList 가 주로 사용됩니다


# ArrayList
자바에서 제공하는 List의 한 형태입니다. 크기가 가변적이라는 특징은 지니고 있으나, 인덱스를 포함하기에 탐색에도 시간을 단축할 수 있습니다

```java
List<Integer> arrayList = new ArrayList<>();
```

## 메서드
| 메서드                                | 설명                  | 시간복잡도 |
|:-----------------------------------|:--------------------|:-----:|
| boolean add(Object object)         | 맨 뒤에 object 추가      | O(1)  |
| void add(int index, Object object) | 해당 index에 object 추가 | O(N)  |
| Object remove(int index)           | 해당 index에 object 제거 | O(N)  |
| Object get(int index)              | 해당 index의 object 반환 | O(1)  |

## 특징
- 끝에만 추가를 하고 제거하지 않을 경우 유리합니다
