import sys
from collections import Counter

def check(bd):

    # OX 개수 세기
    count = Counter(bd)

    o_cnt = count['O']
    x_cnt = count['X']

    game = []
    for i in range(0, 9, 3):
        game.append(bd[i:i+3])

    win_cnt = {'O':0, 'X':0}

    # 가로
    for i in range(3):
        if game[i][0]!='.' and game[i][0]==game[i][1] and game[i][0] == game[i][2]:
            win_cnt[game[i][0]]+=1
    # 세로
    for j in range(3):
        if game[0][j]!='.' and game[0][j]==game[1][j] and game[0][j] == game[2][j]:
            win_cnt[game[0][j]]+=1
    # 대각선
    if game[0][0]!='.' and game[0][0] == game[1][1] and game[0][0] == game[2][2]:
        win_cnt[game[0][0]] += 1

    if game[0][2]!='.' and game[0][2] == game[1][1] and game[0][2] == game[2][0]:
        win_cnt[game[0][2]] += 1

    if win_cnt['O']>=1 and win_cnt['X']>=1: # 둘 다 우승할 수 없음
        return False
    elif (x_cnt-o_cnt)>1: # x 개수가 o 개수보다 2개 이상 많음
        return False
    elif o_cnt>x_cnt: # o가 x보다 많음
        return False
    elif win_cnt['X']>=1 and (x_cnt-o_cnt)==1: # x가 우승하고 x가 1개 더 많음
        return True
    elif win_cnt['O']>=1 and (x_cnt==o_cnt): # o가 우승하고 x와 o 개수가 같음
        return True
    elif win_cnt['O']==0 and win_cnt['X']==0: # x와 o 모두 빙고 없고
        # 다 찬 상태
        if x_cnt==5 and o_cnt==4: # 다 찬 상태애서 x가 하나 더 많음
            return True

    return False


board = []
while True:
    state = sys.stdin.readline().rstrip()
    if state=="end":
        break
    board.append(state)

for bd in board:
    if check(bd):
        print("valid")
    else:
        print("invalid")
      
