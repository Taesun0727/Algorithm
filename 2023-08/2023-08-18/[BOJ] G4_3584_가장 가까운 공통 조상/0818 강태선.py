import sys


def search(num):
    result = [num]
    while parent[num] != 0:
        result.append(parent[num])
        num = parent[num]
    return result


if __name__ == "__main__":
    T = int(sys.stdin.readline())

    for _ in range(T):
        answer = 0
        N = int(sys.stdin.readline())
        parent = [0] * (N + 1)

        for _ in range(N - 1):
            A, B = map(int, sys.stdin.readline().split())
            parent[B] = A

        A, B = map(int, sys.stdin.readline().split())
        parent_a = search(A)
        parent_b = search(B)

        i, j = 0, 0
        if len(parent_a) > len(parent_b):
            i = len(parent_a) - len(parent_b)
        else:
            j = len(parent_b) - len(parent_a)

        while parent_a[i] != parent_b[j]:
            i += 1
            j += 1

        print(parent_a[i])