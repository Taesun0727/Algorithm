import sys

N = int(sys.stdin.readline())
costs = list(list(map(int, sys.stdin.readline().split())) for _ in range(N))

max_size = sys.maxsize
ans = max_size

for i in range(3): # 시작 색
    dp = [[0] * 3 for _ in range(N)]
    dp[0] = [max_size]*3
    dp[0][i] = costs[0][i]
    for j in range(1, N):
        dp[j][0] = min(dp[j-1][1], dp[j-1][2])+costs[j][0]
        dp[j][1] = min(dp[j-1][0], dp[j-1][2])+costs[j][1]
        dp[j][2] = min(dp[j-1][0], dp[j-1][1])+costs[j][2]

    dp[N-1][i] = max_size

    ans = min(ans, min(dp[N-1]))
print(ans)
