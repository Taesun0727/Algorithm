import copy


def check_range(x, y):
    return x < 0 or x >= n or y < 0 or y >= m


visit = [[0] * 5 for _ in range(5)]
block = [[0] * 5 for _ in range(5)]
n, m = 0, 0
dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]


def solve(x1, y1, x2, y2):
    global visit, block
    if visit[x1][y1]:
        return 0
    result = 0

    for i in range(4):
        nx = x1 + dx[i]
        ny = y1 + dy[i]

        if check_range(nx, ny) or visit[nx][ny] or block[nx][ny] == 0:
            continue

        visit[x1][y1] = 1
        value = solve(x2, y2, nx, ny) + 1
        visit[x1][y1] = 0

        if result % 2 == 0 and value % 2 == 1:
            result = value
        elif result % 2 == 0 and value % 2 == 0:
            result = max(result, value)
        elif result % 2 == 1 and value % 2 == 1:
            result = min(result, value)

    return result


def solution(board, aloc, bloc):
    global n, m
    n = len(board)
    m = len(board[0])
    for i in range(n):
        for j in range(m):
            block[i][j] = board[i][j]

    return solve(aloc[0], aloc[1], bloc[0], bloc[1])

