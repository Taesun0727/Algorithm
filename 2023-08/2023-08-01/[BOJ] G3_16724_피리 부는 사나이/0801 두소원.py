import sys


def dfs(x, y, cnt):
    global ans
    if visited[x][y] == 0:
        visited[x][y] = cnt
        if board[x][y] == "U":
            dfs(x - 1, y, cnt)
        elif board[x][y] == "D":
            dfs(x + 1, y, cnt)
        elif board[x][y] == "L":
            dfs(x, y - 1, cnt)
        elif board[x][y] == "R":
            dfs(x, y + 1, cnt)
    else:
        if visited[x][y] == cnt:
            ans+=1
        return


N, M = map(int, sys.stdin.readline().split())
board = list(sys.stdin.readline().rstrip() for _ in range(N))

visited = [[0] * M for _ in range(N)]
cnt = 0
ans = 0
for i in range(N):
    for j in range(M):
        if not visited[i][j]:
            cnt+=1
            dfs(i, j, cnt)

print(ans)
