import sys
sys.setrecursionlimit(10 ** 8)

def DFS(x, y):
    if x == N-1 and y == M-1:
        return 1

    if dp[x][y] != -1:
        return dp[x][y]

    value = 0
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < N and 0 <= ny < M and board[nx][ny] < board[x][y]:
            value += DFS(nx, ny)

    dp[x][y] = value

    return dp[x][y]

if __name__=="__main__":
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    dp = [[-1] * M for _ in range(N)]
    dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]

    print(DFS(0,0))
    print(dp)