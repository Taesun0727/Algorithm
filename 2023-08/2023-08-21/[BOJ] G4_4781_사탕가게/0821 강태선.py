import sys

if __name__ == "__main__":
    while True:
        N, M = map(float, sys.stdin.readline().split())
        N, M = int(N), int(M*100)
        candy = [[] for _ in range(N)]
        if N == 0 and M == 0.00:
            break

        for i in range(N):
            A, B = map(float, sys.stdin.readline().split())
            candy[i] = (int(A), int(B*100+0.5))

        dp = [0] * (M+1)

        for i in range(1, M+1):
            for j in range(N):
                if i < candy[j][1]:
                    continue
                dp[i] = max(dp[i], dp[i-candy[j][1]] + candy[j][0])

        print(dp[M])