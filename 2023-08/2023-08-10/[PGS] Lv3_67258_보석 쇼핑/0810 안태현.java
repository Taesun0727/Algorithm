import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        
        int l = gems.length;
        
        ArrayList<String> gemsList = new ArrayList<>();
        
        for(String gem : gems) {
            if(!gemsList.contains(gem)) gemsList.add(gem);
        }
        
        
        HashMap<String, Integer> gemCnt = new HashMap<>();
        
        int answerStart = -1;
        int answerEnd = -1;
        
        int start = 0;
        int length = Integer.MAX_VALUE;
                
        for(int end = 0; end < l; end++) {
            
            // hashmap에 end 인덱스의 보석 개수 갱신. 안들어있다면 1로 넣기
            gemCnt.put(gems[end], gemCnt.getOrDefault(gems[end], 0) + 1);
            
            // start에 있는 보석이 1개 초과라면 줄여도 됨
            while(gemCnt.get(gems[start]) > 1) {
                gemCnt.put(gems[start], gemCnt.get(gems[start])-1);
                start++;
            }
            
            // 보석이 다 모였다면
            if(gemCnt.size() == gemsList.size() && length > end - start) {
                answerStart = start;
                answerEnd = end;
                length = end - start;
            }
            
            if(start > l - gemsList.size()) break;
        }

        return new int[] {answerStart+1, answerEnd+1};
    }
    
}