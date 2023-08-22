import sys

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]

def union(a, b):
    a = find(a)
    b = find(b)
    if arr[a] < arr[b]:
        parent[b] = a
    else:
        parent[a] = b

if __name__=="__main__":
    N, M, K = map(int, sys.stdin.readline().split())
    parent = [i for i in range(N+1)]
    arr = list(map(int, sys.stdin.readline().split()))
    arr.insert(0, 0)

    for _ in range(M):
        v, w = map(int, sys.stdin.readline().split())
        union(v, w)

    cost = 0
    for idx, root in enumerate(parent):
        if idx == root:
            cost += arr[idx]

    if cost > K:
        print("Oh no")
    else:
        print(cost)


