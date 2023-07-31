import sys
from collections import deque

sys.stdin = open('../input.txt')

def pour(x, y):
    if not visited[x][y]:
        visited[x][y] = True
        queue.append((x, y))

def bfs():
    while queue:
        x, y = queue.popleft()
        z = C - x - y
        if x == 0:
            answer.append(z)

        water = min(x, B - y)
        pour(x - water, y + water)

        water = min(x, C - z)
        pour(x - water, y)

        water = min(y, C - z)
        pour(x, y-water)

        water = min(y, A - x)
        pour(x + water, y - water)

        water = min(z, A - x)
        pour(x+water, y)

        water = min(z, B - y)
        pour(x, y+water)

if __name__=="__main__":
    A, B, C = map(int, sys.stdin.readline().split())
    answer = []
    queue = deque()
    queue.append((0, 0))

    visited = [[False] * (B + 1) for _ in range(A + 1)]
    visited[0][0] = True

    bfs()
    answer.sort()

    for x in answer:
        print(x, end=" ")

