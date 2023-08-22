import sys

if __name__=="__main__":
    N, M = map(int, sys.stdin.readline().split())
    arr = []

    for i in range(N):
        arr.append(int(sys.stdin.readline()))

    start, end = 0, max(arr) * M
    result = max(arr) * M

    while start <= end:
        mid = (start + end) // 2
        total = 0

        for i in range(N):
            total += mid // arr[i]
        if total >= M:
            end = mid - 1
            result = min(result, mid)
        else:
            start = mid + 1

    print(result)


