def check(num, k):
    result = []
    s = ''
    while(num):
        tmp = num % k
        if (tmp == 0) :
            if (len(s) != 0) :
                result.append("".join(reversed(s)))
                s = ''
        else :
            s += str(tmp)
        num //= k
        if (num == 0):
            if (len(s) > 0):
                result.append("".join(reversed(s)))
        
    return result
        
def isPrime(num):
    if num <= 1: return False
    i = 2
    while i*i <= num:
        if num%i == 0: return False
        i += 1
    return True

def solution(n, k):
    answer = 0
    num_list = check(n, k)
    for num in num_list:
        if (isPrime(int(num))):
            answer += 1
    return answer
