import sys
from collections import Counter

N = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().split()))

nums.sort()

answer = 0
for i in range(N):

    new_list = nums[:i]+nums[i+1:]

    left = 0
    right = N-2

    num = nums[i]
    while left<right:
        res = new_list[left]+new_list[right]
        if res<num:
            left+=1
        elif res>num:
            right-=1
        elif res==num:
            answer+=1
            break
print(answer)
