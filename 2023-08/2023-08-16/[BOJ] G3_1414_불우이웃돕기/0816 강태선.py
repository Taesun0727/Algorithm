import sys

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
    N = int(sys.stdin.readline())
    strs = [list(sys.stdin.readline().rstrip()) for _ in range(N)]
    parent = [i for i in range(N+1)]
    graph = []
    total = 0

    for i in range(N):
        for j in range(N):
            if strs[i][j] == 0:
                graph.append((0, i+1, j+1))
            else:
                if ord('a') <= ord(strs[i][j]) <= ord('z'):
                    graph.append((ord(strs[i][j]) - ord('a') + 1, i + 1, j + 1))
                    total += (ord(strs[i][j]) - ord('a') + 1)
                elif ord('A') <= ord(strs[i][j]) <= ord('Z'):
                    graph.append((ord(strs[i][j]) - ord('A') + 27, i + 1, j + 1))
                    total += (ord(strs[i][j]) - ord('A') + 27)

    graph.sort()
    for length, a, b in graph:
        if length ==  0:
            continue
        if find(a) != find(b):
            union(a, b)
            total -= length

    check = True
    for i in range(1, N+1):
        if find(i) != 1:
            check = False

    if check:
        print(total)
    else:
        print(-1)