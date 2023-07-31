import sys

def check(x, y):

    if x==N-1 and y==M-1:
        return 1

    if route[x][y]==-1:
        route[x][y] = 0
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if 0<=nx<N and 0<=ny<M and board[nx][ny]<board[x][y]:
               route[x][y]+=check(nx, ny)

    return route[x][y]


N, M = map(int, sys.stdin.readline().split())
board = list(list(map(int, sys.stdin.readline().split())) for _ in range(N))

dx = [0, -1, 0, 1]
dy = [-1, 0, 1, 0]

route = [[-1]*M for _ in range(N)]

print(check(0,0))
