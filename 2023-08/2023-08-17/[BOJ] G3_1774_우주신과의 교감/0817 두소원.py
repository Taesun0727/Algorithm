import sys


def find(x):
    if x!=parent[x]:
        parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    if x < y:
        parent[y] = x
    else:
        parent[x] = y

N, M = map(int, sys.stdin.readline().split())

nodes =[()]
for _ in range(N):
    nodes.append(tuple(map(int, sys.stdin.readline().split())))

distance = []
for i in range(M):
    x, y = map(int, sys.stdin.readline().split())
    distance.append((0, x, y))

for i in range(1, N):
    for j in range(i+1, N+1):
        dist = ((nodes[i][0]-nodes[j][0])**2+(nodes[i][1]-nodes[j][1])**2)**0.5
        distance.append((dist, i, j))
distance.sort()

parent = [i for i in range(N+1)]
ans = 0
for dist in distance:
    d, x, y = dist

    x = find(x)
    y = find(y)

    if x!=y:
        union(x, y)
        ans+=d

print(f'{ans:.2f}')
