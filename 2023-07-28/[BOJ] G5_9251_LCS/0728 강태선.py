import sys

if __name__=="__main__":
    A = sys.stdin.readline().strip()
    B = sys.stdin.readline().strip()
    dp = [0] * len(B)

    for i in range(len(A)):
        cnt = 0
        for j in range(len(B)):
            if cnt < dp[j]:
                cnt = dp[j]
            elif A[i] == B[j]:
                dp[j] = cnt + 1
    print(max(dp))