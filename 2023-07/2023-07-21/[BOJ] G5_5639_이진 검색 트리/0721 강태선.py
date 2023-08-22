import sys
sys.setrecursionlimit(10**9)

def post(start, end):
    if start > end:
        return
    mid = end + 1
    for i in range(start+1, end+1):
        if Num[i] > Num[start]:
            mid = i
            break

    post(start+1, mid-1)
    post(mid, end)
    print(Num[start])

if __name__=="__main__":
    Num = list()
    while True:
        try:
            N = int(input())
            Num.append(N)
        except:
            break

    post(0, len(Num)-1)