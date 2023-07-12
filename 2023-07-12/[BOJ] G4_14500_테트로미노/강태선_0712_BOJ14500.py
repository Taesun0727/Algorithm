import sys

def DFS(x, y, step, value):
    global result

    if value + max_value*(4-step) <= result:
        return

    if step == 4:
        result = max(result, value)
        return

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny]:
            if step == 2:
                visited[nx][ny] = True
                DFS(x, y, step + 1, value + board[nx][ny])
                visited[nx][ny] = False

            visited[nx][ny] = True
            DFS(nx, ny, step+1, value + board[nx][ny])
            visited[nx][ny] = False

if __name__=="__main__":
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    dx, dy = [-1, 0, 1, 0], [0, 1, 0, -1]
    visited = [[False] * M for _ in range(N)]
    max_value = max(map(max, board))
    result = 0

    for i in range(N):
        for j in range(M):
            visited[i][j] = True
            DFS(i, j, 1, board[i][j])
            visited[i][j] = False

    print(result)