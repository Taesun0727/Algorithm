import sys
sys.setrecursionlimit(10**6)

def dfs(x):
    res = 0
    for i in bridge[x]:
        res+=dfs(i)

    res+=island[x]
    if res<0:
        res = 0

    return res

N = int(sys.stdin.readline())

island = [0]*(N+1)

bridge = list([] for _ in range(N+1))

for i in range(2, N+1):
    t, a, p = sys.stdin.readline().split()
    if t=="S":
        island[i] = int(a)
    else:
        island[i] = -int(a)
    bridge[int(p)].append(i)

ans = 0

print(dfs(1))
