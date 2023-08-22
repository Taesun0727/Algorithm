import sys
from collections import deque
from collections import defaultdict

if __name__=="__main__":
    N = int(sys.stdin.readline())
    arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    result = [0] * N
    times = []
    dp = []
    count = defaultdict(int)
    cond = [[] for _ in range(N)]
    queue = deque()

    for i in range(N):
        times.append(arr[i][0])
        count[i] = len(arr[i])-2
        if len(arr[i]) == 2:
            queue.append(i)
        for j in range(1, len(arr[i])-1):
            cond[arr[i][j]-1].append(i)

    while queue:
        tmp = queue.popleft()
        result[tmp] += times[tmp]

        for x in cond[tmp]:
            count[x] -= 1
            result[x] = max(result[x], result[tmp])
            if count[x] == 0:
                queue.append(x)

    for x in result:
        print(x)