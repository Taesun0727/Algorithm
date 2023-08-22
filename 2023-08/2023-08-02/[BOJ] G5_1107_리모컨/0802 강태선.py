import sys

if __name__ == "__main__":
    num = sys.stdin.readline().rstrip()
    N = int(sys.stdin.readline())
    arr = list(sys.stdin.readline().split())
    result = abs(100 - int(num))

    for x in range(1000001):
        str_x = str(x)

        for tmp in str_x:
            if tmp in arr:
                break
        else:
            result = min(result, len(str_x) + abs(x - int(num)))

    print(result)