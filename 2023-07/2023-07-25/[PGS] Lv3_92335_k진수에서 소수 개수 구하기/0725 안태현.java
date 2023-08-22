import java.util.*;

class Solution {
    public int solution(int n, int k) {
        
        int answer = 0;
        String kNum = Integer.toString(n, k);   // n을 k진수로 변환한 String
        
        String[] nums = kNum.split("0");
        
        for(String s : nums) {
            if(!s.equals("")) {
                if(isPrimeNumber(Long.parseLong(s))) answer++;
            }
        }

        return answer;
    }
    
    private static boolean isPrimeNumber(long num) {
        
        if(num == 1) return false;
        
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        
        return true;
    }

}