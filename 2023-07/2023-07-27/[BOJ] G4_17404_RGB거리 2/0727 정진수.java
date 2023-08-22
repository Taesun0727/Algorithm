package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] dp;
    static int[][] color;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N+1][3];
        color = new int[N+1][3];

        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }   //입력완료

        for(int i=0; i<3; i++){

            for(int j=0; j<3; j++){
                if(i == j) dp[1][j] = color[1][j];
                else dp[1][j] = 1001;
            }

            for(int k=2; k<=N; k++){
                dp[k][0] = Math.min(dp[k-1][1], dp[k-1][2]) + color[k][0];
                dp[k][1] = Math.min(dp[k-1][0], dp[k-1][2]) + color[k][1];
                dp[k][2] = Math.min(dp[k-1][0], dp[k-1][1]) + color[k][2];
            }

            for(int j=0; j<3; j++){
                if(j != i) result = Math.min(result, dp[N][j]);
            }
        }
        System.out.println(result);
    }
}
