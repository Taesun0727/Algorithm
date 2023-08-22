import sys

def find(x):
    if x!=parent[x]:
        parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    if x<y:
        parent[y] = x
    else:
        parent[x] = y


N = int(sys.stdin.readline())
lan = []
total = 0
for i in range(N):
    lengths = sys.stdin.readline().rstrip()
    for j in range(N):
        length = lengths[j]
        if length=='0':
            continue
        elif length.islower():
            res = ord(length)-ord('a')+1
            lan.append((res, i, j))
        elif length.isupper():
            res = ord(length)-ord('A')+27
            lan.append((res, i, j))
        total +=res

lan.sort()

parent = [i for i in range(N)]
ans = 0
edges = 0
for i in lan:
    length, x, y = i

    x = find(x)
    y = find(y)

    if x!=y:
        edges+=1
        union(x, y)
        total-=length

if edges==N-1:
    print(total)
else:
    print(-1)
