def solution(tickets):
    answer = []

    def check(now, res):
        if len(res) == len(tickets) + 1:
            answer.append(res)
            return
        for i, ticket in enumerate(tickets):
            if ticket[0] == now and not visited[i]:
                visited[i] = True
                check(ticket[1], res + [ticket[1]])
                visited[i] = False

    tickets.sort()
    visited = [False] * len(tickets)
    check("ICN", ["ICN"])

    return answer[0]
