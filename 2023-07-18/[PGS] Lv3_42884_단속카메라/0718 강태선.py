import sys
def solution(routes):
    answer = 0
    routes.sort(key=lambda x : x[0])
    min_value = -sys.maxsize
    for route in routes:
        if (route[0] > min_value):
            min_value = route[1]
            answer += 1
        else:
            if (route[1] < min_value):
                min_value = route[1]
    return answer