import sys

if __name__=="__main__":
    N = int(sys.stdin.readline())
    weight_limit = list(map(int, sys.stdin.readline().split()))
    weight_limit.sort(reverse=True)
    box_count = int(sys.stdin.readline())
    box = list(map(int, sys.stdin.readline().split()))
    box.sort(reverse=True)
    if max(box) > max(weight_limit):
        print(-1)
    else:
        time = 0
        while box:
            if not box:
                break

            for weight in weight_limit:
                for box_value in box:
                    if weight >= box_value:
                        box.remove(box_value)
                        break
            time += 1
        print(time)
