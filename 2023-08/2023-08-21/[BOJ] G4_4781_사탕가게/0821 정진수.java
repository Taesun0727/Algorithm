//package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] kcal;
    static int[] cost;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = (int)Math.round(Double.parseDouble(st.nextToken())*100);

            if(n == 0 && m == 0) break;

            kcal = new int[n];
            cost = new int[n];

            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                int candy_kcal = Integer.parseInt(st.nextToken());
                int candy_cost = (int)Math.round(Double.parseDouble(st.nextToken())*100);

                kcal[i] = candy_kcal;
                cost[i] = candy_cost;
            }

            dp = new int[m+1];
            for(int i=0; i<=m; i++){

                for(int j=0; j<n; j++){
                    if (i-cost[j] >= 0) {
                        dp[i] = Math.max(dp[i], dp[i-cost[j]] + kcal[j]);
                    }
                }
            }
            System.out.println(dp[m]);
        }

    }
}
