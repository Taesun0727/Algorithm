import heapq, sys


def solution(n, s, a, b, fares):
    def dijkstra(start):
        hq = []
        costs = [INF] * (n + 1)
        heapq.heappush(hq, (0, start))
        costs[start] = 0

        while hq:
            cost, now = heapq.heappop(hq)

            if costs[now] < cost:
                continue

            for node in nodes[now]:
                c = cost + node[1]
                if c < costs[node[0]]:
                    costs[node[0]] = c
                    heapq.heappush(hq, (c, node[0]))
        return costs

    INF = sys.maxsize
    answer = INF

    nodes = [[] for _ in range(n + 1)]

    for fare in fares:
        c, d, f = fare
        nodes[c].append((d, f))
        nodes[d].append((c, f))

    all_costs = [[]]
    for i in range(1, n + 1):
        all_costs.append(dijkstra(i))

    for i in range(1, n + 1):
        answer = min(answer, all_costs[s][i] + all_costs[i][a] + all_costs[i][b])

    return answer
