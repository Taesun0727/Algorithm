import sys

def find(x):
    if x!=parent[x]:
        parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    if x>y:
        parent[x] = y
    else:
        parent[y] = x

N, M = map(int, sys.stdin.readline().split())
weights = []
for _ in range(M):
    A, B, C = map(int, sys.stdin.readline().split())
    weights.append((C, A, B))

X, Y = map(int, sys.stdin.readline().split())

weights.sort(reverse=True)
parent = [i for i in range(N+1)]

ans = 0
for w in weights:
    C, A, B = w

    A = find(A)
    B = find(B)
    if A!=B:
        union(A,B)
    if find(X)==find(Y):
        print(C)
        break
