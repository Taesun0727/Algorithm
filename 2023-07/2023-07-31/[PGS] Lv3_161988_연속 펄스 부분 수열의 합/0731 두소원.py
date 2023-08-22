import sys


def solution(sequence):
    answer = -sys.maxsize

    size = len(sequence)
    nums1 = [0] * size
    nums2 = [0] * size

    n = 1

    res1 = 0
    res2 = 0

    for i in range(size):
        nums1[i] = sequence[i] * n
        nums2[i] = sequence[i] * -n

        res1 = max(res1 + nums1[i], nums1[i])
        res2 = max(res2 + nums2[i], nums2[i])

        answer = max(res1, res2, answer)
        n *= -1

    return answer
