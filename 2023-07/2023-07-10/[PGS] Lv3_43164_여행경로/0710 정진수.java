package DFSBFS;

import java.io.*;
import java.util.*;

public class 정진수_0710 {
    static boolean[] visited;
    static ArrayList<String> list;
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        int cnt = 0;
        list = new ArrayList<>();
        visited = new boolean[tickets.length];

        dfs("ICN", "ICN", tickets, cnt);
        Collections.sort(list);
        answer = list.get(0).split(" ");
        return answer;
    }
    public void dfs(String start, String route, String[][] tickets, int cnt){
        if(cnt == tickets.length){
            list.add(route);
            return;
        }

        for(int i=0; i<tickets.length; i++){
            if(start.equals(tickets[i][0]) && !visited[i]){
                visited[i] = true;
                dfs(tickets[i][1], route+" "+tickets[i][1],tickets, cnt+1);
                visited[i] = false;
            }
        }
    }
}