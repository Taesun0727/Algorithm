import sys
from collections import defaultdict

if __name__=="__main__":
    N = int(sys.stdin.readline())
    dic = defaultdict(int)
    for _ in range(N):
        A, B = map(int, sys.stdin.readline().split())
        dic[A] += 1
        dic[B] -= 1
    result, count = 0, 0
    start, end = 0, 0
    flag = False

    for i in sorted(dic.keys()):
        count += dic[i]
        if count > result:
            result = count
            start = i
            flag = True
        elif count < result and flag:
            flag = False
            end = i

    print(result)
    print(start, end)