import sys

def change_int(time):
    hour = int(time[:2]) * 3600
    minute = int(time[3:5]) * 60
    second = int(time[6:])
    
    return hour+minute+second

def solution(play_time, adv_time, logs):
    answer = 0
    max_value = -sys.maxsize
    play_time = change_int(play_time)
    adv_time = change_int(adv_time)
    dp = [0] * (play_time+1)
    for i in range(len(logs)):
        tmp = logs[i].split("-")
        dp[change_int(tmp[0])] += 1
        dp[change_int(tmp[1])] -= 1

    for i in range(1, play_time):
        dp[i] += dp[i-1]
    for i in range(1, play_time):
        dp[i] += dp[i-1]
            
    for i in range(adv_time-1, play_time):
        tmp = dp[i] - dp[i-adv_time] 
        if tmp > max_value:
            max_value = tmp
            answer = i - adv_time + 1

    return str(answer // 3600).zfill(2) + ":" + str(answer % 3600 // 60).zfill(2) + ":" + str(answer % 60).zfill(2)
