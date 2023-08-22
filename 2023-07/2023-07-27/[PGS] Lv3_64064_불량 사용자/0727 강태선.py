from itertools import permutations

def check(users, banned_id):
    for i in range(len(banned_id)):
        if len(users[i]) != len(banned_id[i]):
            return False

        for j in range(len(users[i])):
            if banned_id[i][j] == "*":
                continue
            if banned_id[i][j] != users[i][j]:
                return False
    return True


def solution(user_id, banned_id):
    ban = []

    for per_user in permutations(user_id, len(banned_id)):
        if not check(per_user, banned_id):
            continue
        users = set(per_user)
        if users not in ban:
            ban.append(users)
    return len(ban)