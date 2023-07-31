import sys

if __name__=="__main__":
    N, K = map(int, sys.stdin.readline().split())
    arr = list(int(sys.stdin.readline()) for _ in range(N))
    dp = [0 for i in range(K+1)]
    dp[0] = 1

    for i in arr:
         for j in range(i, K+1):
             if j-i >= 0:
                 dp[j] += dp[j-i]

    print(dp[K])

