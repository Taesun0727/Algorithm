import sys
from collections import deque

N = int(sys.stdin.readline())

buildings = [[] for _ in range(N+1)]
degree = [0]*(N+1) # 진입 차수
times = [0]*(N+1) # 걸리는 시간

for i in range(1, N+1):
    nums = list(map(int, sys.stdin.readline().split()))
    times[i] = nums[0]
    for j in range(1,len(nums)-1):
        buildings[nums[j]].append(i)
        degree[i]+=1

queue = deque()
res = [0]*(N+1)

for i in range(1, N+1):
    if degree[i] == 0:
        queue.append(i)
        res[i] = times[i]

while queue:
    x = queue.popleft()

    for i in buildings[x]:
        degree[i]-=1
        res[i] = max(res[i], res[x] + times[i])
        if degree[i]==0:
            queue.append(i)


for i in range(1, N+1):
    print(res[i])
