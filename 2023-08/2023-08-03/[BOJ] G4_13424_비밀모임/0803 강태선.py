import sys
INF = sys.maxsize

if __name__=="__main__":
    for _ in range(int(sys.stdin.readline())):
        N, M = map(int, sys.stdin.readline().split())
        dist = [[INF] * (N+1) for _ in range(N+1)]
        for i in range(1, N + 1):
            dist[i][i] = 0
        for _ in range(M):
            a, b, cost = map(int, sys.stdin.readline().split())
            dist[a][b] = cost
            dist[b][a] = cost

        friends = int(sys.stdin.readline())
        rooms = list(map(int, sys.stdin.readline().split()))

        for k in range(1, N+1):
            for i in range(1, N+1):
                for j in range(1, N+1):
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

        result = [0 for _ in range(N+1)]
        result[0] = INF

        for room in rooms:
            for j in range(1, N+1):
                result[j] += dist[room][j]

        print(result.index(min(result)))


