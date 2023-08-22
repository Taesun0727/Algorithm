import sys
from itertools import combinations

def dfs(L):
    global count

    if L == 15:
        count = 1
        for sum in arr:
            if sum.count(0) != 3:
                count = 0
                break
        return

    game1, game2 = games[L]

    for x, y in ((0, 2), (1, 1), (2, 0)):
        if arr[game1][x] > 0 and arr[game2][y] > 0:
            arr[game1][x] -= 1
            arr[game2][y] -= 1
            dfs(L+1)
            arr[game1][x] += 1
            arr[game2][y] += 1

if __name__ == "__main__":
    answer = []
    games = list(combinations(range(6), 2))

    for _ in range(4):
        tmp = list(map(int, sys.stdin.readline().split()))
        arr = [tmp[i:i+3] for i in range(0, 16, 3)]
        count = 0
        dfs(0)
        answer.append(count)

    print(*answer)
