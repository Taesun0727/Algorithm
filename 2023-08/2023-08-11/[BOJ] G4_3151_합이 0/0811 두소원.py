import sys
from collections import Counter
N = int(sys.stdin.readline())
students = list(map(int, sys.stdin.readline().split()))
counter = Counter(students)
students.sort()
answer = 0
for i in range(N-2):
    left = i+1
    right = N-1

    while left<right:
        res = students[i]+students[left]+students[right]
        if res>0:
            right-=1
        elif res<0:
            left+=1
        else:
            if students[left]==students[right]:
                answer+=right-left
            else:
                cnt = counter[students[right]]
                answer+=cnt
            left+=1

print(answer)
