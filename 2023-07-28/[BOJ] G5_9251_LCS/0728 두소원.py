import sys

A = sys.stdin.readline().rstrip()
B = sys.stdin.readline().rstrip()

dp = [[0]*(len(A)+1) for _ in range(len(B)+1)]

for i in range(1, len(B)+1):
    for j in range(1, len(A)+1):
        if B[i-1]==A[j-1]:
            dp[i][j] = dp[i-1][j-1]+1
        else:
            dp[i][j] = max(dp[i-1][j], dp[i][j-1])

print(dp[-1][-1])
