import sys

if __name__=="__main__":
    T = int(sys.stdin.readline())

    for _ in range(T):
        N, M, K = map(int, sys.stdin.readline().split())
        arr = list(map(int, sys.stdin.readline().split()))
        start, end = 1, M
        current_value = sum(arr[0:M])
        answer = 0

        if current_value < K:
            answer = 1

        if N == M:
            print(answer)
            continue

        while start < N:
            current_value -= arr[start - 1]
            current_value += arr[end % N]
            if current_value < K:
                answer += 1
            start += 1
            end += 1

        print(answer)
