from collections import deque


def solution(board):
    answer = 0

    def bfs(dir):
        queue = deque([(0, 0, 0, dir)])
        N = len(board)
        visited = [[1e9] * N for _ in range(N)]
        visited[0][0] = 0

        # 하우상좌 0123
        dx = [0, 1, 0, -1]
        dy = [1, 0, -1, 0]

        while queue:
            cost, x, y, before = queue.popleft()

            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]

                if 0 <= nx < N and 0 <= ny < N and board[nx][ny] == 0:

                    if i == before:
                        nc = cost + 100
                    else:
                        nc = cost + 600

                    if nc < visited[nx][ny]:
                        visited[nx][ny] = nc
                        queue.append((nc, nx, ny, i))
        return visited[N - 1][N - 1]

    answer = min(bfs(0), bfs(1))

    return answer
