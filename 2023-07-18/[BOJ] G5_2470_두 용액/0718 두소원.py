import sys

N = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().split()))

nums.sort()

left = 0
right = N-1

res = sys.maxsize
answer = [0,0]

while left<right:

    mix = nums[left] + nums[right]

    if abs(mix)<res:
        res = abs(mix)
        answer[0] = nums[left]
        answer[1] = nums[right]

    if mix<0:
        left+=1
    else:
        right-=1


print(*answer)
