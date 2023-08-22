import sys
from collections import deque

def move(a, b):

    queue = deque([(a, b)])
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]
    people = world[a][b]
    union = [(a, b)]
    visited[a][b] = True

    while queue:
        x, y = queue.popleft()

        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]

            if 0<=nx<N and 0<=ny<N and not visited[nx][ny]:
                if L<=abs(world[nx][ny]-world[x][y])<=R:
                    visited[nx][ny] = True
                    queue.append((nx, ny))
                    people += world[nx][ny]
                    union.append((nx, ny))

    if len(union)>1:
        for x, y in union:
            world[x][y] = people//len(union)
        return True
    else:
        return False


N, L, R = map(int, sys.stdin.readline().split())
world = list(list(map(int, sys.stdin.readline().split())) for _ in range(N))
res = 0
while True:
    is_moved = False
    visited = [[False]*N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if not visited[i][j]:
                visited[i][j] = True
                if move(i, j):
                    is_moved = True

    if not is_moved:
        break
    res+=1

print(res)
