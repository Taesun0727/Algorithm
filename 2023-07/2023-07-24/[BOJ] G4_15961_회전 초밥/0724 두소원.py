import sys

N, d, k, c = map(int, sys.stdin.readline().split())
foods = list(int(sys.stdin.readline()) for _ in range(N))

selected = [0]*(d+1)
selected[c] = 1
answer = 1

start = 0
end = k-1

for i in range(k):
    if selected[foods[i]]==0:
        answer+=1
    selected[foods[i]] += 1

res = answer

while start<N:

    selected[foods[start]] -=1
    if selected[foods[start]] ==0:
        res-=1

    start+=1

    end+=1
    if end>=N:
        end = 0
    selected[foods[end]]+=1
    if selected[foods[end]]==1:
        res+=1

    answer = max(res, answer)

print(answer)
