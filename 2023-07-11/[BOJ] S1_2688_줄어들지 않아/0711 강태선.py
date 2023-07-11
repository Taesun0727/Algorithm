import sys

if __name__=="__main__":
    N = int(sys.stdin.readline())

    for i in range(N):
        M = int(sys.stdin.readline())-1
        dp = [1] * 10

        for i in range(M):
            for j in range(10):
                dp[j] = sum(dp[j:])
        print(sum(dp))