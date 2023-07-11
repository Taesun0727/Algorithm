import sys

T = int(sys.stdin.readline())

dp = [[0]*10 for _ in range(1001)] # 행: 자리 수 열: 끝자리 수


for i in range(10): # 한자리수는 모두 1개
    dp[1][i] = 1

for i in range(2, 1001):
    dp[i][0] = 1 # 0으로 끝나는 수는 모두 1개
    for j in range(1, 10):
        # 이전 자리수에서 j로 끝나는 수의 개수 + 현재 자리수에서 이전까지의 개수와 같음
        dp[i][j] = dp[i][j-1]+dp[i-1][j]

for _ in range(T):
    n = int(sys.stdin.readline())
    print(sum(dp[n]))
