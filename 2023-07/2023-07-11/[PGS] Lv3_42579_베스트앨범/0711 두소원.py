def solution(genres, plays):
    answer = []

    songs = {}  # (고유 번호, 재생 횟수)
    total = {}  # 총 재생 횟수

    for i, (genre, play) in enumerate(zip(genres, plays)):
        if genre not in songs:
            songs[genre] = [(i, play)]
            total[genre] = play
        else:
            songs[genre].append((i, play))
            total[genre] += play

    # 총 재생 횟수 순으로 정렬
    total = sorted(total.items(), key=lambda x: x[1], reverse=True)

    for i in range(len(total)):
        song = songs[total[i][0]]
        song.sort(key=lambda x: -x[1])  # 재생횟수 순으로 정렬
        for j in range(len(song)):
            if j == 2:
                break
            answer.append(song[j][0])

    return answer