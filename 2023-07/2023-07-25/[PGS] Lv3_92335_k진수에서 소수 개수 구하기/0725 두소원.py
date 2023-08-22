import math


def solution(n, k):
    answer = 0

    base = ''
    while n > 0:
        n, mod = divmod(n, k)
        base += str(mod)
    base = base[::-1]

    nums = base.split('0')
    for num in nums:
        if num != '':
            if is_prime(int(num)):
                answer += 1
    return answer


def is_prime(num):
    if num == 1:
        return False
    if num == 2:
        return True
    for i in range(2, int(math.sqrt(num)) + 1):
        if num % i == 0:
            return False
    return True
