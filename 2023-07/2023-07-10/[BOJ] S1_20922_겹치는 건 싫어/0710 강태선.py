import sys
from collections import defaultdict

if __name__=="__main__":
    N, M = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    check = defaultdict(int)

    result = 0
    start, end = 0, 0

    while end < N:
        if check[arr[end]] < M:
            check[arr[end]] += 1
            end += 1
        else:
            check[arr[start]] -= 1
            start += 1
        result = max(result, end-start)

    print(result)



