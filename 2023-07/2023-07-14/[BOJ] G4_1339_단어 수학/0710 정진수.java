package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 0710 정진수 {
    static int N;
    static int[] alpha = new int[26];
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            String s = br.readLine();

            int tmp = (int)Math.pow(10, s.length()-1);

            for(int j=0; j<s.length(); j++) {
                alpha[(int) s.charAt(j) - 65] += tmp;
                tmp /= 10;
            }
        }

        Arrays.sort(alpha);
        int num  = 9;
        for(int i=alpha.length-1; i>=0; i--){
            if(alpha[i] != 0){
                result += alpha[i]*num;
                num--;
            }
        }
        System.out.println(result);
    }
}
