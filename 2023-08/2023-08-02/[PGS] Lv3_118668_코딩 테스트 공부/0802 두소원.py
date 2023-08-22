import sys


def solution(alp, cop, problems):
    max_alp, max_cop = 0, 0
    for pb in problems:
        max_alp = max(max_alp, pb[0])
        max_cop = max(max_cop, pb[1])

    if max_alp <= alp and max_cop <= cop:
        return 0
    if max_alp <= alp:
        alp = max_alp
    if max_cop <= cop:
        cop = max_cop

    # dp[alp][cop] (alp, cop) 도달하는데 걸리는 시간
    dp = [[sys.maxsize] * (max_cop + 1) for _ in range(max_alp + 1)]

    dp[alp][cop] = 0

    for i in range(alp, max_alp + 1):
        for j in range(cop, max_cop + 1):
            if i + 1 <= max_alp:
                dp[i + 1][j] = min(dp[i][j] + 1, dp[i + 1][j])  # 알고리즘 공부로 알고력+1
            if j + 1 <= max_cop:
                dp[i][j + 1] = min(dp[i][j] + 1, dp[i][j + 1])  # 코딩 공부로 코딩력+1

            # 문제 하나 선택
            for pb in problems:
                alp_req, cop_req, alp_rwd, cop_rwd, cost = pb
                if i >= alp_req and j >= cop_req:
                    nx_alp = min(max_alp, i + alp_rwd)
                    nx_cop = min(max_cop, j + cop_rwd)
                    dp[nx_alp][nx_cop] = min(dp[i][j] + cost, dp[nx_alp][nx_cop])

    return dp[-1][-1]
  
