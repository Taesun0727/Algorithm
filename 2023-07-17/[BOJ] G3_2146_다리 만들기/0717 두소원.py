import sys
from collections import deque

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def find_land(a, b, idx): # 섬 구분하기
    queue = deque([(a, b)])
    board[a][b] = idx

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if 0<=nx<N and 0<=ny<N and board[nx][ny]==1:
                board[nx][ny] = idx
                queue.append((nx, ny))

def bridge(now): # 다리 놓기
    global res

    queue = deque()
    dist = [[-1]*N for _ in range(N)]

    for i in range(N):
        for j in range(N):
            if board[i][j]==now:
                queue.append((i, j))
                dist[i][j] = 0

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if 0<=nx<N and 0<=ny<N:
                if board[nx][ny] > 0 and board[nx][ny] != now:
                    res = min(res, dist[x][y])
                    return

                if board[nx][ny]==0 and dist[nx][ny]==-1:
                    queue.append((nx, ny))
                    dist[nx][ny] = dist[x][y]+1


N = int(sys.stdin.readline())
board = list(list(map(int, sys.stdin.readline().split())) for _ in range(N))

idx = 2
for i in range(N):
    for j in range(N):
        if board[i][j]==1:
            find_land(i, j, idx)
            idx+=1

print(board)
res = 100000

for i in range(2, idx):
    bridge(i)

print(res)
