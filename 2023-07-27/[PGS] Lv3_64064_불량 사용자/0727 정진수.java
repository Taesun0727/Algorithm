package DFSBFS;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    static ArrayList<List<String>> noc = new ArrayList<>();
    static public int solution(String[] user_id, String[] banned_id) {
        boolean[] visited = new boolean[user_id.length];

        dfs(user_id, banned_id, visited, 0, 0);
        return noc.size();
    }

    private static void dfs(String[] user_id, String[] banned_id, boolean[] visited, int start, int cnt) {
        if(cnt == banned_id.length){
            List<String> list = new ArrayList<>();
            for(int i=0; i<visited.length; i++){
                if(visited[i]) list.add(user_id[i]);
            }
            if(!noc.contains(list)) noc.add(list);

            return;
        }

        for(int i=start; i<banned_id.length; i++){
            for(int j=0; j<user_id.length; j++){
                String ban = banned_id[i];
                String user = user_id[j];

                boolean check = true;

                if(ban.length() != user.length()) check = false;
                else {
                    for(int k=0; k<ban.length(); k++){
                        if(ban.charAt(k) == '*') continue;
                        if(ban.charAt(k) != user.charAt(k)) {
                            check = false;
                            break;
                        }
                    }
                }

                if(check && !visited[j]){
                    visited[j] = true;
                    dfs(user_id, banned_id, visited, i+1, cnt+1);
                    visited[j] = false;
                }
            }
        }

    }

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] ban_id = {"fr*d*", "abc1**"};
        System.out.println(solution(user_id, ban_id));
    }
}
