import sys
from collections import defaultdict

sys.stdin = open('../input.txt')

if __name__=="__main__":
    N = int(sys.stdin.readline())
    dic = defaultdict(int)
    dic2 = defaultdict(int)
    words = []
    for i in range(N):
        str = sys.stdin.readline().rstrip()
        words.append(str)
        digit = 10 ** (len(str)-1)
        for i in range(0, len(str)):
            dic[str[i]] += digit // 10**i

    dic = sorted(dic.items(), key = lambda x : x[1], reverse=True)

    count = 9
    for key, value in dic:
        dic2[key] = count
        count -= 1

    result = 0
    for word in words:
        digit = 10 ** (len(word)-1)
        for i in range(len(word)):
            result += dic2[word[i]] * (digit // (10 ** i))

    print(result)