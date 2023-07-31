import sys

N = int(sys.stdin.readline())
snows = list(map(int, sys.stdin.readline().split()))

snows.sort()

ans = sys.maxsize

for i in range(N-3):
    for j in range(i+3, N):
        left = i + 1
        right = j - 1
        while left<right:

            res = (snows[i]+snows[j])-(snows[left]+snows[right])
            ans = min(ans, abs(res))

            if res>0:
                left+=1
            else:
                right-=1

print(ans)
