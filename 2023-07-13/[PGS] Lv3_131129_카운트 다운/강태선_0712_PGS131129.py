import sys

sys.setrecursionlimit(10 ** 7)

INF = 2147000000

def solution(target):
    def throw(num):
        if dp[num][0] == INF:
            if num >= 50:
                tmp = throw(num - 50)
                if tmp[0] + 1 < dp[num][0] or (tmp[0] + 1 == dp[num][0] and tmp[1] + 1 > dp[num][1]):
                    dp[num][0] = tmp[0] + 1
                    dp[num][1] = tmp[1] + 1

            start = num
            if num >= 20:
                start = 20

            for i in range(start, 0, -1):
                for j in range(1, 4):
                    if num >= i * j:
                        tmp = throw(num - i * j)
                        count = 0
                        if j == 1:
                            count = 1
                        if tmp[0] + 1 < dp[num][0] or (tmp[0] + 1 == dp[num][0] and tmp[1] + count > dp[num][1]):
                            dp[num][0] = tmp[0] + 1
                            dp[num][1] = tmp[1] + count

        return dp[num]

    dp = list([INF, 0] for _ in range(100001))
    dp[0][0] = 0
    return throw(target)