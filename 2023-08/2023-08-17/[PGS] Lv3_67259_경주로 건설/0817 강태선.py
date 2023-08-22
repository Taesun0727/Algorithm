import sys
from heapq import heappop, heappush

def solution(board):
    INF = sys.maxsize
    N = len(board)
    delta = [[-1, 0], [1, 0], [0, 1], [0, -1]]
    visited = [[[INF] * 4 for _ in range(N)] for _ in range(N)]

    heap = [(0, 0, 1, 0), (0, 0, 2, 0)]

    while heap:
        x, y, dire, cost = heappop(heap)

        for i in range(4):
            nx = x + delta[i][0]
            ny = y + delta[i][1]
            if i == dire:
                ncost = cost + 100
            else:
                ncost = cost + 600

            if 0 > nx or nx >= N or 0 > ny or ny >= N or board[nx][ny] or visited[nx][ny][i] < ncost:
                continue

            heappush(heap, (nx, ny, i, ncost))
            visited[nx][ny][i] = ncost

    return min(visited[N - 1][N - 1][0], visited[N - 1][N - 1][1], visited[N - 1][N - 1][2], visited[N - 1][N - 1][3])