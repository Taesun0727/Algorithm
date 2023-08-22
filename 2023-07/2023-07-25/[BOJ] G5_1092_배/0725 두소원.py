import sys

N = int(sys.stdin.readline())
crains = sorted(list(map(int, sys.stdin.readline().split())), reverse=True)
M = int(sys.stdin.readline())
boxes = sorted(list(map(int, sys.stdin.readline().split())), reverse=True)

ans = 0

if crains[0]<boxes[0]:
    print(-1)
else:
    while boxes:
        for crain in crains:
            for box in boxes:
                if box<=crain:
                    boxes.remove(box)
                    break

        ans+=1

    print(ans)
