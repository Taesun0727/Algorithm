def solution(gems):
    N = len(gems)
    answer = [1, N]
    len_gems = len(set(gems))
    gem_count = len(set(gems));
    dic = {gems[0]: 1}
    i = 0
    j = 0
    while (i < N and j < N):
        if (len(dic)) < gem_count:
            j += 1
            if (j == N):
                break
            if (gems[j] in dic):
                dic[gems[j]] += 1
            else:
                dic[gems[j]] = 1
        else:
            if (j - i < answer[1] - answer[0]):
                answer[0] = i + 1
                answer[1] = j + 1
            if (dic[gems[i]] == 1):
                del dic[gems[i]]
            else:
                dic[gems[i]] -= 1
            i += 1

    return answer