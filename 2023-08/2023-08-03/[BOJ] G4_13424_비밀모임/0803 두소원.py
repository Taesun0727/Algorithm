import sys

T = int(sys.stdin.readline())
for _ in range(T):
    N, M = map(int, sys.stdin.readline().split())

    costs = [[sys.maxsize]*(N+1) for _ in range(N+1)]

    for _ in range(M):
        a, b, c = map(int, sys.stdin.readline().split())
        costs[a][b] = c
        costs[b][a] = c

    for x in range(1, N+1):
        costs[x][x] = 0
        for i in range(1, N+1):
            for j in range(1, N+1):
                costs[i][j] = min(costs[i][j], costs[i][x]+costs[x][j])

    K = int(sys.stdin.readline())
    friends = list(map(int, sys.stdin.readline().split()))

    ans = 0
    res = sys.maxsize

    for i in range(1, N+1):
        s = 0
        for fr in friends:
            s += costs[fr][i]
        if s<res:
            ans = i
            res = s

    print(ans)
