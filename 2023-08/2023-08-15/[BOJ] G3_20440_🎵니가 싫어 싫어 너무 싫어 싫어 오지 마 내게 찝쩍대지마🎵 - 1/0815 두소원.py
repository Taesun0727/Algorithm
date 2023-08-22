import sys
from collections import defaultdict
N = int(sys.stdin.readline())

time = defaultdict(int)
for _ in range(N):
    x, y = map(int, sys.stdin.readline().split())
    time[x]+=1
    time[y]-=1

sort_time = sorted(time)

start, end = 0, 0
max_cnt = 0
total = 0
for key in sort_time:
    total += time[key]

    if total>max_cnt:
        max_cnt = total
        start = key
        flag = True

    elif total<max_cnt and flag:
        end = key
        flag = False
print(max_cnt)
print(start, end)
