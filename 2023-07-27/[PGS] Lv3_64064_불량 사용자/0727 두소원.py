from itertools import permutations

def solution(user_id, banned_id):
    res = []

    for perm in permutations(user_id, len(banned_id)):
        for i in range(len(banned_id)):
            if not checkId(perm[i], banned_id[i]):
                break
        else:
            perm = set(perm)
            if perm not in res:
                res.append(perm)

    return len(res)


def checkId(user, ban):
    if len(user) != len(ban):
        return False

    for i in range(len(user)):
        if ban[i] == "*":
            continue
        else:
            if user[i] != ban[i]:
                return False
    return True
