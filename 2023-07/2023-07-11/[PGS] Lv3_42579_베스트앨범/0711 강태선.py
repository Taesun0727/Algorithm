from collections import defaultdict

def solution(genres, plays):
    answer = []
    dic1 = defaultdict(list)
    dic2 = defaultdict(int)

    for i, (genere, play) in enumerate(zip(genres, plays)):
        dic1[genere].append((i, play))
        dic2[genere] += play

    for key, value in sorted(dic2.items(), key=lambda x: x[1], reverse=True):
        for index, play in sorted(dic1[key], key=lambda x: x[1], reverse=True)[:2]:
            answer.append(index)

    return answer