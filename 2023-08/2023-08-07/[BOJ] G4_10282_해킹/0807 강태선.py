import sys
from heapq import heappush, heappop

INF = sys.maxsize

def dijkstra(start):
    heappush(heap, [start, 0])
    dp[start] = 0
    while heap:
        a, s = heappop(heap)
        for next, weight in graph[a]:
            next_weight = s + weight
            if next_weight < dp[next]:
                dp[next] = next_weight
                heappush(heap, (next, next_weight))

if __name__=="__main__":
    T = int(sys.stdin.readline())

    for _ in range(T):
        N, M, infection = map(int, sys.stdin.readline().split())
        graph = [[] for _ in range(N+1)]
        dp = [INF] * (N+1)
        heap = []

        for _ in range(M):
            a, b, s = map(int, sys.stdin.readline().split())
            graph[b].append((a, s))

        dijkstra(infection)

        result = [0, 0]
        for x in dp:
            if x != INF:
                result[0] += 1
                result[1] = max(result[1], x)
        print(result[0], result[1])
