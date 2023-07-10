import sys

N, M = map(int, sys.stdin.readline().split())
times = [0]*N
for i in range(N):
    times[i] = int(sys.stdin.readline())

# 심사 했을 때 가능한 최대 최소 시간
start = min(times) # 1명 심사시 가능한 최소 시간
end = max(times)*M # M명 심사시 가능한 최대 시간

answer = sys.maxsize
while start<=end:
    mid = (start+end)//2

    res = 0
    # 처리 가능한 인원수 구하기
    for i in range(N):
        res+=mid//times[i]


    if res>=M: # 가능한 인원수 M보다 크다면
        end=mid-1
        answer = min(answer, mid)
    else:
        start = mid+1

print(answer)
