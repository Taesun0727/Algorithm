import sys
from collections import deque


def solution(n, roads, sources, destination):
    def bfs():

        queue = deque([(destination, 0)])
        visited = [False] * (n + 1)
        visited[destination] = True
        distance = [-1] * (n + 1)

        while queue:
            x, dist = queue.popleft()
            distance[x] = dist

            for i in graph[x]:
                if not visited[i]:
                    visited[i] = True
                    queue.append((i, dist + 1))

        return distance

    answer = []

    graph = [[] for _ in range(n + 1)]

    for road in roads:
        graph[road[0]].append(road[1])
        graph[road[1]].append(road[0])

    distance = bfs()

    for source in sources:
        answer.append(distance[source])

    return answer
