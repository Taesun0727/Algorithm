def solution(n, costs):
    def find(x):
        if x != parent[x]:
            x = find(parent[x])
        return parent[x]

    def union(x, y):
        if x < y:
            parent[y] = x
        else:
            parent[x] = y

    answer = 0

    parent = [i for i in range(n)]

    costs.sort(key=lambda x: x[2])

    for cost in costs:
        a, b, c = cost

        a = find(a)
        b = find(b)

        if a != b:
            union(a, b)
            answer += c

    return answer
