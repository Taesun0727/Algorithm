import sys
sys.setrecursionlimit(10**8)

def dfs(L):
    result = 0
    for i in tree[L]:
        result += dfs(i)

    result += node[L]

    if result < 0:
        result = 0

    return result

if __name__=="__main__":
    N = int(sys.stdin.readline())
    tree = [[] for _ in range(N+1)]
    node = [0] * (N+1)

    for i in range(2, N+1):
        t, a, b = sys.stdin.readline().split()
        tree[int(b)].append(i)

        if t == 'W':
            node[i] -= int(a)
        else:
            node[i] = int(a)

    print(dfs(1))
