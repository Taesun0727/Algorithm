from heapq import heappush, heappop

def solution(jobs):
    answer = 0
    now = 0
    count = 0
    start = -1
    heap = []

    while count < len(jobs):
        for j in jobs:
            if start < j[0] <= now:
                heappush(heap, (j[1], j[0]))
        if len(heap) > 0:
            current = heappop(heap)
            start = now
            now += current[0]
            answer += (now - current[1])
            count += 1
        else:
            now += 1

    answer = int(answer / len(jobs))

    return answer