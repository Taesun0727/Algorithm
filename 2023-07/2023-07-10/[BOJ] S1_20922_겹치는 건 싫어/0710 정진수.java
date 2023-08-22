package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정진수_0710 {
    static int N, K;
    static int[] list;
    static int[] cnt;
    static int start, end;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new int[N];
        cnt = new int[100001];
        result = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        while (start < N){
            if(cnt[list[start]] != K){
                cnt[list[start]]++;
                start++;
            }
            else {
                cnt[list[end]]--;
                end++;
            }

            result = Math.max(result, start-end);
        }
        System.out.println(result);
    }
}
