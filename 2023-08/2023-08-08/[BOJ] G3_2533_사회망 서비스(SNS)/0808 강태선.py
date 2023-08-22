import sys
from collections import defaultdict

sys.setrecursionlimit(10**9)

def DFS(node):
    for next in dic[node]:
        if not visited[next]:
            visited[next] = True
            DFS(next)
            dp[node][0] += dp[next][1]
            dp[node][1] += min(dp[next])

if __name__=="__main__":
    N = int(sys.stdin.readline())
    dic = defaultdict(list)

    for _ in range(N-1):
        a, b = map(int, sys.stdin.readline().split())
        dic[a].append(b)
        dic[b].append(a)

    dp = [[0, 1] for _ in range(N+1)]
    visited = [False] * (N+1)

    visited[1] = True
    DFS(1)

    print(min(dp[1]))
