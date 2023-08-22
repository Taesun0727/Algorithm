import sys
from collections import deque

def find():
    res = -1 # 맨 처음 토마토도 시간에 포함이 돼서 -1부터 시작함
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    while queue:
        for _ in range(len(queue)):
            x, y = queue.popleft()
            for i in range(4):
                nx = x+dx[i]
                ny = y+dy[i]
                if 0<=nx<N and 0<=ny<M and box[nx][ny]==0:
                    box[nx][ny] = 1
                    queue.append((nx, ny))
        res += 1 # +1일

    return res

M, N = map(int, sys.stdin.readline().split())
box = list(list(map(int, sys.stdin.readline().split())) for _ in range(N))
queue = deque([])

for i in range(N):
    for j in range(M):
        if box[i][j] ==1:
            queue.append((i, j))

ans = find()

# 하나라도 익지 않았으면 -1
for i in range(N):
    for j in range(M):
        if box[i][j] ==0:
            ans = -1
            break
print(ans)
