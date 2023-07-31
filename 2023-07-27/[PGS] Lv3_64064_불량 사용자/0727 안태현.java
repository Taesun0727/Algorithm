import java.util.*;

class Solution {
    private static int bannedIdLength, userIdLength;
    private static String[] userIds, bannedIds;
    private static boolean[] isSelected;
    private static HashSet<String> hashset;
    
    public int solution(String[] user_id, String[] banned_id) {

        // 전역변수로 설정
        userIdLength = user_id.length;
        bannedIdLength = banned_id.length;
        userIds = user_id;
        bannedIds = banned_id;
        
        isSelected = new boolean[userIdLength];
        hashset = new HashSet<String>();
        
        dfs(0);
        
        return hashset.size();
    }
    
    private static void dfs(int bannedIdIndex) {
        if(bannedIdIndex == bannedIdLength) {
            String store = "";
            for(int i = 0; i < isSelected.length; i++) {
                if(isSelected[i]) store += "T";
                else store += "F";
            }
            hashset.add(store);
            return;
        }
        
        for(int i = 0; i < userIdLength; i++) {
            if(!isSelected[i] && isPossible(userIds[i], bannedIds[bannedIdIndex])) {
                isSelected[i] = true;
                dfs(bannedIdIndex+1);
                isSelected[i] = false;
            }
        }
        
    }
    
    private static boolean isPossible(String userId, String bannedId) {
        if(userId.length() != bannedId.length()) return false;
        
        for(int i = 0; i < userId.length(); i++) {
            if(bannedId.charAt(i) == '*') continue;
            if(userId.charAt(i) != bannedId.charAt(i)) return false;
        }
        return true;
    }
}