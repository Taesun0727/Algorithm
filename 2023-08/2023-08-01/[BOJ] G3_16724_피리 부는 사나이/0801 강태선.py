import sys
from collections import defaultdict

def find(n):
    if parent[n] != n:
        parent[n] = find(parent[n])
    return parent[n]

def union(a, b):
    a = find(a)
    b = find(b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

if __name__=="__main__":
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline())) for _ in range(N)]
    visited = defaultdict(bool)
    parent = [i for i in range(N*M)]
    delta = {'L':[0, -1], 'R':[0, 1], "D":[1, 0], "U":[-1, 0]}
    result = 0

    for i in range(N*M):
        x = i // M
        y = i % M
        nx = x + delta[board[x][y]][0]
        ny = y + delta[board[x][y]][1]
        next_num = nx*M + ny
        union(i, next_num)

    for i in range(N*M):
        if find(parent[i]) not in visited:
            result += 1
            visited[parent[i]] = True
    print(result)