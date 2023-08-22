import sys
from collections import deque

if __name__=="__main__":
    N, L, R = map(int, sys.stdin.readline().split())
    arr = []
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    for i in range(N):
        arr.append(list(map(int, sys.stdin.readline().split())))

    def process(x, y):
        global is_move
        united = []
        united.append((x, y))
        queue = deque()
        queue.append((x, y))
        check[x][y] = True
        total_value = arr[x][y]
        count = 1

        while queue:
            tmp = queue.popleft()

            for i in range(4):
                nx = tmp[0] + dx[i]
                ny = tmp[1] + dy[i]

                if 0 <= nx < N and 0 <= ny < N and not check[nx][ny] and L <= abs(arr[tmp[0]][tmp[1]] - arr[nx][ny]) <= R:
                    queue.append((nx, ny))
                    check[nx][ny] = True
                    total_value += arr[nx][ny]
                    count += 1
                    united.append((nx, ny))

        if count > 1:
            is_move = True
            for x, y in united:
                arr[x][y] = total_value // count

        return

    result = 0

    while True:
        is_move = False
        check = [[False] * N for _ in range(N)]

        for i in range(N):
            for j in range(N):
                if not check[i][j]:
                    process(i, j)

        if is_move:
            result += 1
        else:
            break

    print(result)





