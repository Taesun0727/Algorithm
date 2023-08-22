import sys

def find(x):
    if x!=parent[x]:
        parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    x = find(x)
    y = find(y)

    if money[x-1]<money[y-1]:
        parent[y] = x

    else:
        parent[x] = y

N, M, K = map(int, sys.stdin.readline().split())
money = list(map(int, sys.stdin.readline().split()))

parent = [i for i in range(N+1)]

for i in range(M):
    v, w= map(int, sys.stdin.readline().split())
    if v==w or parent[v]==parent[w]:
        continue
    union(v, w)

res = 0
for i in range(1, N+1):
    if parent[i]==i:
        res+=money[i-1]

if res>K:
    print("Oh no")
else:
    print(res)
