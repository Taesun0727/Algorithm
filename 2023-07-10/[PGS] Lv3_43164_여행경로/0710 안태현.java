import java.util.*;

class Solution {
    
    boolean[] isUsedTickets;
    String[] answer;
    boolean isComplete;
    
    public String[] solution(String[][] tickets) {
        
        isUsedTickets = new boolean[tickets.length];
        answer = new String[tickets.length + 1];
        
        // 도착지를 기준으로 이차원 배열을 정렬
        Arrays.sort(tickets, (o1, o2) -> o1[1].compareTo(o2[1]));
        
        dfs(0, "ICN", tickets);
        
        return answer;
    }
    
    void dfs(int cnt, String destination, String[][] tickets) {
        if(cnt == tickets.length) {
            answer[cnt] = destination;
            isComplete = true;
            return;
        }
        
        for(int i = 0; i < tickets.length; i++) {
            if(!isUsedTickets[i] && destination.equals(tickets[i][0])) {
                answer[cnt] = destination;
                isUsedTickets[i] = true;
                dfs(cnt+1, tickets[i][1], tickets);
                if(isComplete) break;
                isUsedTickets[i] = false;
            }
        }
    }
}