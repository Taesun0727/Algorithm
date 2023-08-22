def solution(board, skill):
    answer = 0
    prefix_sum = [[0] * (len(board[0])+1) for _ in range(len(board)+1)]
    N = len(prefix_sum)-1
    M = len(prefix_sum[0])-1
    
    for t, s_x, s_y, e_x, e_y, cost in skill:
        if t == 1:
            cost = -cost
        prefix_sum[s_x][s_y] += cost
        prefix_sum[s_x][e_y+1] -= cost
        prefix_sum[e_x+1][s_y] -= cost
        prefix_sum[e_x+1][e_y+1] += cost
        
    for i in range(N):
        for j in range(M):
            prefix_sum[i][j+1] += prefix_sum[i][j]
    
    for i in range(M):
        for j in range(N):
            prefix_sum[j+1][i] += prefix_sum[j][i]
            
    for i in range(len(board)):
        for j in range(len(board[i])):
            if board[i][j] + prefix_sum[i][j] > 0:
                answer += 1
            
    return answer
