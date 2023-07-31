package Implementation;

import java.util.Arrays;

public class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String number = conversion(n, k);
        String[] list = number.split("0");

        System.out.println(Arrays.toString(list));

        for(int i=0; i<list.length; i++){
            if(!list[i].equals("") && Long.parseLong(list[i]) > 1){
                if(isPrime(Long.parseLong(list[i]))){
                    answer++;
                }
            }
        }
        return answer;
    }
    public static String conversion(int number, int K){
        StringBuilder sb = new StringBuilder();
        int cur = number;

        while(cur > 0){
            if(cur % K < K) sb.append(cur%K);
            else sb.append((char)(cur%K-10+'A'));

            cur /= K;
        }

        return sb.reverse().toString();
    }

    public static boolean isPrime(long x){
        for(int i=2; i<=(int)Math.sqrt(x); i++){
            if(x%i == 0) return false;
        }
        return true;
    }
}
