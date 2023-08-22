import sys, heapq


def dijkstra(start):
    hq = []
    heapq.heappush(hq, (0, start))
    distance = [INF]*(N+1)
    distance[start] = 0

    while hq:
        dist, now = heapq.heappop(hq)

        if distance[now]<dist:
            continue

        for node in graph[now]:
            d = dist+node[1]
            if d<distance[node[0]]:
                distance[node[0]] = d
                heapq.heappush(hq, (d, node[0]))
    return distance


N, M, X = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(N+1)]
for _ in range(M):
    a, b, t = map(int, sys.stdin.readline().split())
    graph[a].append((b, t))

INF = sys.maxsize

students = [[]]
for i in range(1, N+1):
    students.append(dijkstra(i))

answer = 0
for i in range(1, N+1):
    answer = max(answer, students[i][X]+students[X][i])

print(answer)
