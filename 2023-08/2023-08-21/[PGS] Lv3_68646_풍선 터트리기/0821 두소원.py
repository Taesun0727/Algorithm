def solution(a):
    answer = 0

    res = [False] * (len(a))
    left_min = 1e9
    right_min = 1e9
    for i in range(len(a)):
        if a[i] < left_min:
            left_min = a[i]
            res[i] = True
        if a[-i - 1] < right_min:
            right_min = a[-i - 1]
            res[-i - 1] = True
    answer = sum(res)
    return answer
