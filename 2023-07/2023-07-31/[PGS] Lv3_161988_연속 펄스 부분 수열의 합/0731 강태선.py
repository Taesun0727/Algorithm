def solution(sequence):
    prefix_sum = [[0 for _ in range(len(sequence)+1)] for _ in range(2)]
    dp = [[0 for _ in range(len(sequence)+1)] for _ in range(2)]
    tmp = 1
    
    for i in range(len(sequence)):
        prefix_sum[0][i+1] = sequence[i] * tmp
        prefix_sum[1][i+1] = -sequence[i] * tmp
        tmp *= -1
    sequence.insert(0, 0)
    
    for i in range(len(sequence)-1):
        dp[0][i+1] = max(dp[0][i] + prefix_sum[0][i+1], prefix_sum[0][i+1])
        dp[1][i+1] = max(dp[1][i] + prefix_sum[1][i+1], prefix_sum[1][i+1])

    return max(max(dp[0]),max(dp[1]))