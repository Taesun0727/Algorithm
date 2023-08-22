import sys
from collections import deque
from collections import defaultdict

if __name__=="__main__":
    N, M = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(N+1)]
    degree = defaultdict(int)
    queue = deque()
    result = []
    for _ in range(M):
        a, b = map(int, sys.stdin.readline().split())
        graph[a].append(b)
        degree[b] += 1

    for i in range(1, N+1):
        if degree[i] == 0:
            queue.append(i)

    while queue:
        tmp = queue.popleft()
        result.append(tmp)

        for num in graph[tmp]:
            degree[num] -= 1
            if degree[num] == 0:
                queue.append(num)

    print(*result)