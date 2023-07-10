def solution(tickets):
    answer = []
    visited = [False] * len(tickets)
    tickets.sort(key=lambda x: (x[0], x[1]))

    def Travel(tic, route):
        if len(route) == len(tickets) + 1:
            answer.append(route)
            return
        for idx, ticket in enumerate(tickets):
            if tic == ticket[0] and visited[idx] == False:
                visited[idx] = True
                Travel(ticket[1], route + [ticket[1]])
                visited[idx] = False

    Travel("ICN", ["ICN"])

    return answer[0]
