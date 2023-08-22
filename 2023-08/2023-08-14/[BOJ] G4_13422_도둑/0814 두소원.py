import sys

T = int(sys.stdin.readline())

for _ in range(T):
    N, M, K = map(int, sys.stdin.readline().split())
    house = list(map(int, sys.stdin.readline().split()))

    answer = 0

    left = 0
    right = M-1

    total = sum(house[:M])
    if N==M:
        if total<K:
            answer+=1
        print(answer)
        continue
    while left<N:
        print(left, right)
        if total<K:
            answer+=1
        total-=house[left]

        left+=1
        right+=1
        if right==N:
            right=0
        total+=house[right]
    print(answer)
