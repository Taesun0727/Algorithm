from collections import defaultdict


def solution(gems):
    answer = [0, len(gems)]

    gems_set = set(gems)
    gems_dict = defaultdict(int)

    size = len(set(gems))

    left = 0
    right = len(gems_set)-1
    for i in range(right+1):
        gems_dict[gems[i]] += 1

    while left < len(gems) and right<len(gems):
        if len(gems_dict)==size:
            if right-left<answer[1]-answer[0]:
                answer = [left+1, right+1]
            else:
                gems_dict[gems[left]] -= 1
                if gems_dict[gems[left]] == 0:
                    del gems_dict[gems[left]]
                left += 1
        else:
            right += 1
            if right==len(gems):
                break
            gems_dict[gems[right]] += 1

    return answer
