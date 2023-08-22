def rotate(key):
    len_key = len(key)
    rotate_key = [[0] * len_key for _ in range(len_key)]
    for i in range(len_key):
        for j in range(len_key):
            rotate_key[j][len_key - i - 1] = key[i][j]
    return rotate_key


def check(lock):
    len_lock = len(lock) // 3
    for i in range(len_lock, len_lock * 2):
        for j in range(len_lock, len_lock * 2):
            if lock[i][j] != 1:
                return False
    return True


def solution(key, lock):
    len_key = len(key)
    len_lock = len(lock)
    expansion_lock = [[0] * (len_lock * 3) for _ in range(len_lock * 3)]

    for i in range(len_lock):
        for j in range(len_lock):
            expansion_lock[i + len_lock][j + len_lock] = lock[i][j]

    for _ in range(4):
        key = rotate(key)
        for i in range(1, len_lock * 2):
            for j in range(1, len_lock * 2):
                for k in range(len_key):
                    for l in range(len_key):
                        expansion_lock[i + k][j + l] += key[k][l]
                if check(expansion_lock):
                    return True
                for k in range(len_key):
                    for l in range(len_key):
                        expansion_lock[i + k][j + l] -= key[k][l]
    return False