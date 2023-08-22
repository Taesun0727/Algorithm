from collections import defaultdict
from collections import deque

def solution(n, lighthouse):
    answer = 0
    graph = defaultdict(list)
    check = [0] * (n + 1)

    for a, b in lighthouse:
        graph[a].append(b)
        graph[b].append(a)

    queue = deque()
    for i in range(1, n + 1):
        if len(graph[i]) == 1:
            queue.append(i)

    while queue:
        child = queue.popleft()
        if graph[child] == []:
            break
        parent = graph[child][0]

        del graph[child]
        graph[parent].remove(child)

        if len(graph[parent]) == 1:
            queue.append(parent)

        if check[child] == 0:
            check[parent] = 1

    answer = sum(check)

    return answer