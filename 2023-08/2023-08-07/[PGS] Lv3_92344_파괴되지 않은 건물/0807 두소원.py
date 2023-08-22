def solution(board, skill):
    answer = 0

    degrees = [[0] * (len(board[0]) + 1) for _ in range(len(board) + 1)]

    for sk in skill:
        ty, r1, c1, r2, c2, degree = sk
        if ty == 1:
            degree = degree * -1

        degrees[r1][c1] += degree
        degrees[r1][c2 + 1] -= degree
        degrees[r2 + 1][c1] -= degree
        degrees[r2 + 1][c2 + 1] += degree

    for i in range(len(degrees) - 1):
        for j in range(len(degrees[0]) - 1):
            degrees[i][j + 1] += degrees[i][j]

    for j in range(len(degrees[0]) - 1):
        for i in range(len(degrees) - 1):
            degrees[i + 1][j] += degrees[i][j]

    for i in range(len(board)):
        for j in range(len(board[0])):
            if degrees[i][j] + board[i][j] > 0:
                answer += 1

    return answer
