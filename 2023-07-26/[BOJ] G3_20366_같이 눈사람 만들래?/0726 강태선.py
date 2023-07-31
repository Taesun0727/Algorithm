import sys
import itertools

if __name__=="__main__":
    N = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    index = [i for i in range(len(arr))]
    snowman = []
    result = sys.maxsize

    for a, b in itertools.combinations(index, 2):
        snowman.append((arr[a]+arr[b], a, b))

    snowman.sort()

    for i in range(1, len(snowman)):
        if snowman[i][1] != snowman[i-1][1] and snowman[i][1] != snowman[i-1][2] and snowman[i][2] != snowman[i-1][1] and snowman[i][2] != snowman[i-1][2]:
            result = min(result, abs(snowman[i][0] - snowman[i-1][0]))

    print(result)
