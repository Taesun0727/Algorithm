//package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] list;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new int[N];
        result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }   //입력완료

        Arrays.sort(list);

        int start = 0;
        int end = N-1;

        for(int i=0; i<N; i++){
            binarySearch(list, list[i], start, end, i);
        }

        System.out.println(result);
    }

    private static void binarySearch(int[] list, int target, int start, int end, int idx) {


        if(start == idx) start++;
        if(end == idx) end--;

        if(start >= end) return;


        if(list[start] + list[end] == target) {
            result++;
            return;
        }

        else if(list[start] + list[end] > target) binarySearch(list, target, start, end-1, idx);

        else binarySearch(list, target, start+1, end, idx);
    }
}
