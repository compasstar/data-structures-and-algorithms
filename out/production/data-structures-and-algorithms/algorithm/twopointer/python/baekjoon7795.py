"""
- A의 크기가 B보다 큰 쌍이 몇 개나 있는지 구하라
- A리스트를 돌면서 자기보다 작은 걸 하나씩 체크한다
- 그냥 다 돌면 시간초과!!! (4억)

- 정렬을 하고 투포인터로 돌아보는 건 어때?
- {1 1 3 7 8} {1 3 6}

2. 시간복잡도
- O(N) = 20000 ==> 통과

3. 자료구조
- 우선 정렬
- A 배열을 for문으로 돈다
- A 인덱스 값이 B인덱스 값보다 클 때까지 넘긴다. (while문)
- 더 큰 상태일 때는 B의 인덱스를 올려가며 센다
- B 값이 다시 더 커지면 A의 인덱스를 높여가며 끝까지 반복한다
"""
import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    N, M = map(int, input().split())
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))

    cnt = 0
    A.sort()
    B.sort()

    b_index = 0
    for a in A:
        while (b_index < M and a > B[b_index]):
            b_index += 1
        cnt += b_index
        
    
    print(cnt)
    
