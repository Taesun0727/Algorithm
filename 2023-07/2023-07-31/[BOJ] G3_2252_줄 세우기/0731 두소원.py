import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())

students = [[] for _ in range(N + 1)]
degree = [0] * (N + 1)

for _ in range(M):
    A, B = map(int, sys.stdin.readline().split())
    students[A].append(B)
    degree[B] += 1

queue = deque()
res = []

for i in range(1, N + 1):
    if degree[i] == 0:
        queue.append(i)

while queue:
    x = queue.popleft()
    res.append(x)

    for i in students[x]:
        degree[i]-=1

        if degree[i]==0:
            queue.append(i)

print(*res)
