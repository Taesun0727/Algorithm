//package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] dp;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2];

        list = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }
        check(1, 0);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void check(int curr, int child) {

        dp[curr][0] = 0;
        dp[curr][1] = 1;

        for(int next : list[curr]){

            //단말노드가 아닐 때
            if(next != child){

                check(next, curr);

                dp[curr][0] += dp[next][1];
                dp[curr][1] += Math.min(dp[next][0], dp[next][1]);


            }
        }
    }
}
