import sys
from heapq import heappush, heappop

def dijkstra(start):
    heap = []
    heappush(heap, (0, start))

    while heap:
        cost, now = heappop(heap)
        cost *= -1

        if now == end:
            print(cost)
            break
        if dp[now] > cost:
            continue
        for tmp in graph[now]:
            if cost == 0:
                dp[tmp[0]] = tmp[1]
                heappush(heap, (-dp[tmp[0]], tmp[0]))
            elif dp[tmp[0]] < tmp[1] and dp[tmp[0]] < cost:
                dp[tmp[0]] = min(tmp[1], cost)
                heappush(heap, (-dp[tmp[0]], tmp[0]))


if __name__ == "__main__":
    N, M = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(N + 1)]

    for _ in range(M):
        a, b, cost = map(int, sys.stdin.readline().split())
        graph[a].append((b, cost))
        graph[b].append((a, cost))

    start, end = map(int, sys.stdin.readline().split())
    dp = [0] * (N + 1)
    dijkstra(start)