import sys

def get_dist(x1, y1, x2, y2):
    return ((x1-x2) ** 2 + (y1-y2) ** 2) ** 0.5

def union(a, b):
    a = find(a)
    b = find(b)
    if a > b:
        parent[a] = b
    else:
        parent[b] = a


def find(n):
    if n != parent[n]:
        parent[n] = find(parent[n])
    return parent[n]


if __name__ == "__main__":
    N, M = map(int, sys.stdin.readline().split())
    parent = [i for i in range(N+1)]
    edges = []
    coors = []

    for _ in range(N):
        a, b = map(int, sys.stdin.readline().split())
        coors.append((a, b))

    for _ in range(M):
        a, b = map(int, sys.stdin.readline().split())
        union(a, b)

    for i in range(N):
        for j in range(i+1, N):
            edges.append((get_dist(coors[i][0], coors[i][1], coors[j][0], coors[j][1]), i, j))

    edges.sort()
    answer = 0

    for edge in edges:
        cost, a, b = edge[0], edge[1]+1, edge[2]+1

        if find(a) != find(b):
            union(a, b)
            answer += cost

    print("{:.2f}".format(answer))