import sys

def Row(x, a):
    for i in range(9):
        if board[x][i] == a:
            return False
    return True

def Col(y, a):
    for i in range(9):
        if board[i][y] == a:
            return False
    return True

def Rec(x, y, a):
    nx = x // 3 * 3
    ny = y // 3 * 3
    for i in range(3):
        for j in range(3):
            if a == board[nx+i][ny+j]:
                return False
    return True

def DFS(L):
    if L == len(blank):
        for i in range(9):
            print(*board[i])
        exit(0)

    for i in range(1, 10):
        x = blank[L][0]
        y = blank[L][1]

        if Row(x, i) and Col(y, i) and Rec(x, y ,i):
            board[x][y] = i
            DFS(L+1)
            board[x][y] = 0

if __name__=="__main__":
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(9)]
    blank = []

    for i in range(9):
        for j in range(9):
            if board[i][j] == 0:
                blank.append((i, j))

    DFS(0)

