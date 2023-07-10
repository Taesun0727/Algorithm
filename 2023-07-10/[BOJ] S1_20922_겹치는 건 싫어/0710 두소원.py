import sys

N, K = map(int, sys.stdin.readline().split())
nums = list(map(int, sys.stdin.readline().split()))

start = 0
end = 0

keys = set(nums) # 등장하는 수 찾기
count = {}
for key in keys: # 각각의 수의 개수 dictionary에 0으로 초기화
    count[key]=0

answer = 0
count[nums[0]]+=1

while end+1<N:
    if count[nums[end+1]]<K:
        end+=1
        count[nums[end]]+=1
    else:
        count[nums[start]]-=1
        start+=1
    answer = max(answer, end-start+1)

print(answer)
