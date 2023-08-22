import sys
from heapq import heappush, heappop
INF = sys.maxsize
def dijkstra(start):
    heap = []
    heappush(heap, (0, start))
    distance = [INF] * (N+1)
    distance[start] = 0

    while heap:
        current_cost, current = heappop(heap)

        if distance[current] < current_cost:
            continue

        for next, next_cost in graph[current]:
            cost = current_cost + next_cost
            if distance[next] > cost:
                distance[next] = cost
                heappush(heap, (cost, next))

    return distance

if __name__=="__main__":
    N, M, X = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(N+1)]

    for _ in range(M):
        A, B, cost = map(int, sys.stdin.readline().split())
        graph[A].append((B, cost))

    result = -INF

    for i in range(1, N+1):
        go = dijkstra(i)
        back = dijkstra(X)
        result = max(result, go[X] + back[i])

    print(result)