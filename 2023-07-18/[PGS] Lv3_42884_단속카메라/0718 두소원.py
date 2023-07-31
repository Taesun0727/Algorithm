def solution(routes):
    answer = 0
    routes.sort(key=lambda x: x[1])  # 진출 지점 오름차순
    last = -30001  # 마지막 카메라가 있는 지점

    for route in routes:
        # 카메라가 진입 지점보다 전에 있으면 하나 더 세우기
        if route[0] > last:
            last = route[1]
            answer += 1

    return answer
