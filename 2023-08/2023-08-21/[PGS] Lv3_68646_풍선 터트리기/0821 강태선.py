import sys

def solution(a):
    check = [0] * len(a)
    start, end = sys.maxsize, sys.maxsize

    for i in range(len(a)):
        if a[i] < start:
            start = a[i]
            check[i] = 1
        if a[-1-i] < end:
            end = a[-1-i]
            check[-1-i] = 1
            
    return sum(check)