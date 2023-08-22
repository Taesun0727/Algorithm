import sys, heapq

def dijkstra(start):
    hq = []
    heapq.heappush(hq, (0, start))
    time[start] = 0

    while hq:
        dist, now = heapq.heappop(hq)

        if time[now]<dist:
            continue

        for c in computer[now]:
            t = dist+c[1]
            if t < time[c[0]]:
                time[c[0]] = t
                heapq.heappush(hq, (t, c[0]))

T = int(sys.stdin.readline())
INF = sys.maxsize
for _ in range(T):
    n, d, c = map(int, sys.stdin.readline().split())

    computer = [[] for _ in range(n+1)]
    time = [INF]*(n+1)
    for _ in range(d):
        a, b, s = map(int, sys.stdin.readline().split())
        computer[b].append((a, s))


    dijkstra(c)
    res = 0
    total_time = 0
    for i in range(1, n+1):
        if time[i]!=INF:
            res+=1
            total_time = max(total_time, time[i])
    print(res, total_time)
