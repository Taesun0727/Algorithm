import sys

def find_min_board(color):
    num_sum = [[0] * (M + 1) for _ in range(N + 1)]

    for i in range(N):
        for j in range(M):
            if (i + j) % 2 == 0:
                value = board[i][j] != color
            else:
                value = board[i][j] == color
            num_sum[i + 1][j + 1] = num_sum[i][j + 1] + num_sum[i + 1][j] - num_sum[i][j] + value

    cnt = sys.maxsize
    for i in range(1, N - K + 2):
        for j in range(1, M - K + 2):
            cnt = min (cnt, num_sum[i + K - 1][j + K - 1] - num_sum[i + K - 1][j - 1] - num_sum[i - 1][j + K - 1] + num_sum[i - 1][j - 1])

    return cnt

if __name__=="__main__":
    N, M, K = map(int, sys.stdin.readline().split())
    board = [list(input()) for _ in range(N)]
    print(min(find_min_board('B'), find_min_board('W')))
