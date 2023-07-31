import sys
sys.setrecursionlimit(10**6)

def find(start, end):
    if start>end:
        return

    mid = end+1

    for i in range(start+1, end+1):
        if nodes[start]<nodes[i]:
            mid = i
            break

    find(start+1, mid-1)
    find(mid, end)
    print(nodes[start])


nodes = []
while True:
    try:
        nodes.append(int(sys.stdin.readline()))
    except:
        break
find(0, len(nodes)-1)
