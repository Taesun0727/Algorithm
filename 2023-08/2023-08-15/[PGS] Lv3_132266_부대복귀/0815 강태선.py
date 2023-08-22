from collections import deque

def solution(n, roads, sources, destination):
    answer = []
    graph = [[] for _ in range(n + 1)]
    visited = [-1] * (n + 1)
    for road in roads:
        graph[road[0]].append(road[1])
        graph[road[1]].append(road[0])

    queue = deque([destination])
    visited[destination] = 0

    while queue:
        tmp = queue.popleft()

        for road in graph[tmp]:
            if visited[road] == -1:
                visited[road] = visited[tmp] + 1
                queue.append(road)

    for source in sources:
        answer.append(visited[source])

    return answer