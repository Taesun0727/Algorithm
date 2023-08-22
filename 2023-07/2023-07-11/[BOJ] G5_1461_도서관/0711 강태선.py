import sys

if __name__=="__main__":
    N, M = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    left = []
    right = []
    distance = []
    result = 0

    for x in arr:
        if x > 0:
            right.append(x)
        else:
            left.append(x)

    left.sort()
    right.sort(reverse=True)

    for i in range(len(left) // M):
        distance.append(abs(left[i*M]))
    if len(left) % M > 0:
        distance.append(abs(left[(len(left)//M) * M]))
    for i in range(len(right) // M):
        distance.append(abs(right[i*M]))
    if len(right) % M > 0:
        distance.append(right[(len(right)//M) * M])

    distance.sort()

    result = distance.pop() + sum(distance) * 2

    print(result)