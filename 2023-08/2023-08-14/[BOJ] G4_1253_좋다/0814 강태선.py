import sys

if __name__=="__main__":
    N = int(sys.stdin.readline())
    answer = 0
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()

    for i in range(N):
        ex_arr = arr[:i] + arr[i+1:]
        start, end = 0, len(ex_arr)-1

        while start < end:
            tmp = ex_arr[start] + ex_arr[end]
            if tmp == arr[i]:
                answer += 1
                break
            if tmp < arr[i]:
                start += 1
            else:
                end -= 1
                
    print(answer)
