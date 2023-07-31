import sys
from collections import defaultdict

if __name__=="__main__":
    n, d, k, c = map(int, sys.stdin.readline().split())

    arr = [int(sys.stdin.readline()) for _ in range(n)]

    start, end = 0, k-1
    dic = defaultdict(int)
    dic[c] += 1
    result = -sys.maxsize
    for i in range(end+1):
        dic[arr[i]]+= 1

    while start < n:
        result = max(result, len(dic))

        dic[arr[start]] -= 1
        if dic[arr[start]] == 0:
            del dic[arr[start]]

        start += 1
        end += 1
        dic[arr[end % n]] += 1

    print(result)