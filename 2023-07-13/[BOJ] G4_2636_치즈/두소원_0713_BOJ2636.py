import sys
from collections import deque

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]


def find():

    queue = deque([(0,0)])
    visited[0][0] = True

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny]:
                if board[nx][ny] == 0:
                    visited[nx][ny] = True
                    queue.append((nx, ny)) # 치즈가 아닐 때만 큐에 append
                elif board[nx][ny] == 1:
                    board[nx][ny] = 0 # 치즈라면 녹이고 append 안함
                    visited[nx][ny] = True


N, M = map(int, sys.stdin.readline().split())
board = list(list(map(int, sys.stdin.readline().split())) for _ in range(N))
time = 0
res_list=[]
while True:
    res = 0
    for i in range(N): # 남은 개수
        res+=sum(board[i])
    res_list.append(res)
    if res == 0:
        break
    visited = [[False] * M for _ in range(N)]
    find()


if len(res_list) == 1:
    cheese = 0
else:
    cheese = res_list[-2]

print(len(res_list)-1)
print(cheese)
