import sys


def check(idx):
    if idx == len(zeros):
        for b in board:
            print(*b)
        exit(0)

    x, y = zeros[idx]
    for i in range(1, 10):
        if not row[x][i] and not col[y][i] and not sqr[x//3*3+y//3][i]:
            row[x][i] = col[y][i] = sqr[x//3*3+y//3][i] = True
            board[x][y] = i
            check(idx+1)
            row[x][i] = col[y][i] = sqr[x//3*3+y//3][i] = False
            board[x][y] = 0


board = list(list(map(int, sys.stdin.readline().split())) for _ in range(9))

zeros = []

row = [[False]*10 for _ in range(9)]
col = [[False]*10 for _ in range(9)]
sqr = [[False]*10 for _ in range(9)]

for i in range(9):
    for j in range(9):
        num = board[i][j]
        if num==0:
            zeros.append((i, j))
        else:
            row[i][num] = True
            col[j][num] = True
            sqr[i//3*3 + j//3][num] = True

check(0)
