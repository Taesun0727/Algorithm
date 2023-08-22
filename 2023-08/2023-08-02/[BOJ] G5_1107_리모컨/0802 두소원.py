import sys

def find():

    res = abs(N-100)

    for i in range(1000001):
        if check(str(i)):
            res = min(res, abs(N-i)+len(str(i)))


    print(res)

def check(ch):
    for i in ch:
        if i in buttons:
            return False
    return True


N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
if M == 0:
    print(min(len(str(N)), abs(N-100)))
else:
    buttons = list(sys.stdin.readline().split())
    find()
