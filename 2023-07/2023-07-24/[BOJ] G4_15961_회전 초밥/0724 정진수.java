package SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//99%에서 틀림 계속
public class Main {
    static int N, d, k, c;
    static int[] list;
    static int[] eat_sushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        list = new int[N];
        eat_sushi = new int[d+1];
        for(int i=0; i<N; i++){
            list[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        int result;

        for(int i=0; i<k; i++){
            if(eat_sushi[list[i]] == 0) cnt++;
            eat_sushi[list[i]]++;
        }
        result = cnt;

        for(int i=1; i<N; i++){

            eat_sushi[list[i-1]]--;
            if(eat_sushi[list[i-1]] == 0) cnt--;

            if(eat_sushi[list[(i+k-1)%N]] == 0) cnt++;
            eat_sushi[list[(i+k-1)%N]]++;

            if(cnt >= result){
                if(eat_sushi[c] == 0) result = cnt+1;
                else result = cnt;
            }
        }
        System.out.println(result);
    }

}
