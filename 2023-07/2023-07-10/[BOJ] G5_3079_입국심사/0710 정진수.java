package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 정진수_0710 {
    static int N, M;
    static long[] list;
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //심사대 수
        M = Integer.parseInt(st.nextToken());   //인원 수

        list = new long[N];
        result = Long.MAX_VALUE;

        for(int i=0; i<N; i++){
            long t = Long.parseLong(br.readLine());
            list[i] = t;
        }

        Arrays.sort(list);

        long minT = 0;
        long maxT = M * list[list.length-1];

        while (minT <= maxT){
            long people = 0;
            long midT = (minT + maxT) / 2;

            for(int i=0; i<list.length; i++){
                if(people >= M) break;
                people += midT / list[i];
            }

            if(people >= M){
                maxT = midT-1;
                result = Math.min(result, midT);
            }
            else {
                minT = midT+1;
            }
        }
        System.out.println(result);
    }

}
