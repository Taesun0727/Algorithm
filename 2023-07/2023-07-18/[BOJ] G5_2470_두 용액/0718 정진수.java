package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] list;
    static int result_min, result_max;
    static int sum, temp, blend;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        list = new int[N];
        result_min = 0;
        result_max = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);
        blend = Integer.MAX_VALUE;
        int start = 0;
        int end = list.length-1;

        while(start < end){

            int sum = list[start] + list[end];
            int temp = Math.abs(sum);

            if(temp < blend){
                blend = temp;
                result_min = list[start];
                result_max = list[end];
            }

            if(sum > 0) end--;
            else start++;
        }

        System.out.print(result_min+" "+result_max);
    }
}
