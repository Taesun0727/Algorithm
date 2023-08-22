import java.util.*;

class Solution {
    // problems: {요구알고력, 요구코딩력, 증가알고력, 증가코딩력, 소요시간}
    public int solution(int alp, int cop, int[][] problems) {
        
        // 문제를 다 풀수 있을 알고력, 코딩력 구하기
        int alpMax = Integer.MIN_VALUE;
        int copMax = Integer.MIN_VALUE;
        for(int[] problem : problems) {            
            alpMax = Math.max(alpMax, problem[0]);
            copMax = Math.max(copMax, problem[1]);
        }
        
        // 이미 문제를 다 풀 수 있는 능력치를 가지고 있다면 0 리턴
        if(alp >= alpMax && cop >= copMax) return 0;
        // 알고력만 높다면 알고력을 최대요구알고력으로 바꾸기(dp 배열 탐색을 위해서)
        else if(alp > alpMax) alp = alpMax;
        // 코딩력만 높다면 코딩력을 최대요구알고력으로 바꾸기(dp 배열 탐색을 위해서)
        else if(cop > copMax) cop = copMax;
        
        int[][] dp = new int[alpMax+2][copMax+2];
        // 알고력과 코딩력이 증가해야하는 풀수 있는 부분은 max로 설정
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                 if(alp < i || cop < j) dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int i = alp; i < dp.length-1; i++) {
            for(int j = cop; j < dp[i].length-1; j++) {
                
                    // 지금 능력치로 풀 수 있는 문제 풀고 실력 키우자
                    for(int[] problem : problems) {

                        if(i >= problem[0] && j >= problem[1]) {
                            
                            // 지금 문제를 풀면 걸리는 시간
                            int time = dp[i][j] + problem[4];
                            
                            // 능력치가 초과한다면 그 전 문제는 풀 수 있는거잖아
                            if(i+problem[2] > alpMax && j+problem[3] > copMax) {
                                dp[alpMax][copMax] = Math.min(dp[alpMax][copMax], time);
                            }
                            // 근데 알고력만 초과하는거라면?
                            else if(i+problem[2] > alpMax) {
                                dp[alpMax][j+problem[3]] = Math.min(dp[alpMax][j+problem[3]], time);
                            }
                            // 코딩력만 초과하는거라면?
                            else if(j+problem[3] > copMax) {
                                dp[i+problem[2]][copMax] = Math.min(dp[i+problem[2]][copMax], time);
                            }
                            // 둘 다 초과안하면 그냥 저장해!
                            else {
                                dp[i+problem[2]][j+problem[3]] = Math.min(dp[i+problem[2]][j+problem[3]], time);
                            }
                        }

                    } 
                
                    // 기다려서 알고력을 올릴거니?
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);

                    // 기다려서 코딩력을 올릴거니?
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                    
            }
        }
        
        print(dp);
        
        return dp[alpMax][copMax];
    }
    
    private static void print(int [][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
}