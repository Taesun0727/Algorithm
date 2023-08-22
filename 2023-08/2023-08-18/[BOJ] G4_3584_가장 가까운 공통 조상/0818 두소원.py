import sys

T = int(sys.stdin.readline())

for _ in range(T):
    N = int(sys.stdin.readline())

    parent = [0]*(N+1)
    for _ in range(N-1):
        a, b = map(int, sys.stdin.readline().split())
        parent[b] = a
    x, y = map(int, sys.stdin.readline().split())

    x_parent = [x]
    y_parent = [y]

    while parent[x]:
        x_parent.append(parent[x])
        x = parent[x]

    while parent[y]:
        y_parent.append(parent[y])
        y = parent[y]

    idx_x = len(x_parent)-1
    idx_y = len(y_parent)-1

    while x_parent[idx_x]==y_parent[idx_y]:
        idx_x-=1
        idx_y-=1

    print(x_parent[idx_x+1])
