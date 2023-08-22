N, M, size = 0, 0, 0
l = []
def solution(key, lock):
    global N, M, size
    answer = False

    N = len(key)
    M = len(lock)

    size = N * 2 + M - 2 # lock 확장했을 때의 가로 세로 크기

    for i in range(M):
        for j in range(M):
            if lock[i][j] == 0: # 자물쇠의 홈이면
                l.append([i + (N - 1), j + (N - 1)]) # 좌표 변경해서 저장

    for _ in range(4):
        k, key = rotate(key) # 회전하기 -> k: 열쇠 돌기, key: 회전한 후의 열쇠
        if check(k):
            return True
        if move(k):
            return True

    return answer


# 회전
def rotate(key):
    k = []
    key_copy = [[0] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            key_copy[i][j] = key[N - j - 1][i]
            if key_copy[i][j] == 1:
                k.append([i, j])

    return k, key_copy


# 움직이기
def move(key):

    # 확장된 자물쇠의 좌표 안에서 움직이기
    for i in range(size - (N - 1)):
        k_copy = []
        for a in range(len(key)):
            k_copy.append([key[a][0], key[a][1]])

        for x in range(len(k_copy)):
            k_copy[x][0] += i

        for j in range(size - (N - 1)):
            for x in range(len(k_copy)):
                k_copy[x][1] += 1
            if check(k_copy):
                return True

    return False


# 확인
def check(k):
    res = 0
    for x in k:
        if N - 1 <= x[0] < N - 1 + M and N - 1 <= x[1] < N - 1 + M:
            if x not in l: # 자물쇠와 겹치는데 자물쇠의 홈이 아니면
                return False
            else: # 자물쇠의 홈이면
                res += 1

    if res == len(l):
        return True
    else:
        return False
