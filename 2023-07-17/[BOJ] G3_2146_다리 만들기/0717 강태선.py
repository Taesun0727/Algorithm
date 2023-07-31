import sys
sys.setrecursionlimit(300000)
from collections import deque

dx, dy = [-1, 0, 1, 0], [0, 1, 0 ,-1]

def change_num(x, y, count):
    board[x][y] = count
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < N and 0 <= ny < N and board[nx][ny] == 1:
            change_num(nx, ny, count)

def find(n):
    global answer
    dist = [[-1] * N for _ in range(N)]
    queue = deque()

    for i in range(N):
        for j in range(N):
            if board[i][j] == n:
                queue.append((i, j))
                dist[i][j] = 0

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= N or ny < 0 or ny >= N:
                continue

            if board[nx][ny] > 0 and board[nx][ny] != n:
                answer = min(answer, dist[x][y])
                return

            if board[nx][ny] == 0 and dist[nx][ny] == -1:
                dist[nx][ny] = dist[x][y] + 1
                queue.append((nx, ny))

if __name__=="__main__":
    N = int(sys.stdin.readline())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    count = 2
    for i in range(N):
        for j in range(N):
            if board[i][j] == 1:
                change_num(i, j, count)
                count += 1

    answer = sys.maxsize

    for i in range(2, count):
        find(i)

    print(answer)

