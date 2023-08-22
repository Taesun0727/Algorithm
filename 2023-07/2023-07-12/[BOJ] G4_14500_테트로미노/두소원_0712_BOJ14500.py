import sys

dx = [0, -1, 0, 1]
dy = [-1, 0, 1, 0]

ans = 0

def check(x, y, res, cnt):
    global ans

    if res+(4-cnt)*board_max<ans: # 백트래킹
        return
    if cnt==4:
        ans = max(res, ans)
        return

    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]

        if 0<=nx<N and 0<=ny<M and not visited[nx][ny]:
            if cnt==2: # ㅜ 모양 만들기
                visited[nx][ny] = True
                check(x, y, res+board[nx][ny], cnt+1)
                visited[nx][ny] = False
            visited[nx][ny]=True
            check(nx, ny, res+board[nx][ny], cnt+1)
            visited[nx][ny] = False

N, M = map(int, sys.stdin.readline().split())
board = list(list(map(int,sys.stdin.readline().split())) for _ in range(N))
board_max = max(map(max, board))
visited = [[False]*M for _ in range(N)]

for i in range(N):
    for j in range(M):
        visited[i][j] = True
        check(i, j, board[i][j],1)
        visited[i][j] = False

print(ans)
