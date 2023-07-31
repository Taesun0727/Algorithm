import sys

N = int(sys.stdin.readline())
words = list((sys.stdin.readline().strip()) for _ in range(N))

count = {}

for word in words:
    for i in range(len(word)):
        if word[i] not in count:
            count[word[i]]= 10**(len(word)-i-1)
        else:
            count[word[i]] +=10**(len(word)-i-1)

nums = sorted(count.items(), key = lambda x:x[1], reverse = True)

res = 0
idx = 9

for num in nums:
    res+=num[1]*idx
    idx-=1

print(res)
