import sys
sys.setrecursionlimit(10**6)
def find(node):
    visited[node] = True
    for n in friends[node]:
        if not visited[n]:
            find(n)
            dp[node][0] += dp[n][1]
            dp[node][1] += min(dp[n])



N = int(sys.stdin.readline())

friends = [[] for _ in range(N+1)]
for _ in range(N-1):
    u, v = map(int, sys.stdin.readline().split())
    friends[u].append(v)
    friends[v].append(u)

visited = [False]*(N+1)
dp = [[0, 1] for _ in range(N+1)]
find(1)
print(min(dp[1]))
