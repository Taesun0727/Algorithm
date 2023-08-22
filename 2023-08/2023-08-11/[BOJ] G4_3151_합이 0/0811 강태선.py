import sys
from collections import Counter

if __name__=="__main__":
    N = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()
    cnt = Counter(arr)
    result = 0

    for index, value in enumerate(arr):
        start, end = index+1, N-1

        while start < end:
            sum_value = arr[index] + arr[start] + arr[end]
            if sum_value > 0:
                end -= 1
            else:
                if sum_value == 0:
                    if arr[start] == arr[end]:
                        result += end - start
                    else:
                        result += cnt[arr[end]]
                start += 1

    print(result)
