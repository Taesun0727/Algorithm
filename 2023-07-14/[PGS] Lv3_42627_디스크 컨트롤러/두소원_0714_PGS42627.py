import heapq


def solution(jobs):
    answer = 0  # 총 걸린 시간

    start, now, idx = -1, 0, 0  # 이전 작업 시작 시간, 현재 시간, 처리한 일의 개수
    disk = []  # 처리할 순서대로 담음

    while idx < len(jobs):
        for job in jobs:
            if start < job[0] <= now:
                # 작업 요청이 들어오는 시간이 이전 작업의 시작 시간 이후이고 현재 시간보다 이전
                heapq.heappush(disk, [job[1], job[0]])
        print(disk)

        if len(disk) > 0:
            work = heapq.heappop(disk)
            start = now
            now += work[0]
            answer += now - work[1]
            idx += 1
        else:
            now += 1

    return answer // len(jobs)
