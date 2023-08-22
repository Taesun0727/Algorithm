//package SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N, M, K;
    static int[] house;
    static int[] steal_money;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            house = new int[N];
            steal_money = new int[N+1];
            result = 0;

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                house[i] = Integer.parseInt(st.nextToken());
            }   //입력완료

            //default 값 => 3, 4, 7
            int sum = 0;
            for(int i=0; i<M; i++){
                sum += house[i];
            }

            if(N == M && sum < K) result = 1;
            else {
                if(sum < K) result++;

                for(int i=1; i<N; i++){
                    sum = sum - house[(i-1)%N] + house[(i+M-1)%N];

                    if(sum < K) result++;
                }
            }

            System.out.println(result);
        }
    }
}
