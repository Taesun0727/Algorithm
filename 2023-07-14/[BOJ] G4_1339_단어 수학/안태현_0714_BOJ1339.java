import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] times = new int[26];  //각 알파벳의 자릿수 크기 확인

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            int ten = 1;
            for(int j = s.length()-1; j >= 0; j--) {
                times[s.charAt(j) - 'A'] += ten;
                ten *= 10;
            }
        }

        Arrays.sort(times); // 오름차순으로 정렬

        // 사용된 알파벳 개수 세기
        int cnt = 0;
        for(int i : times) {
            if(i > 0) cnt++;
        }

        int answer = 0;
        int multiple = 9;

        for(int i = 25; i > 16; i--) {
            if(times[i] == 0) break;
            answer += times[i] * multiple;
            multiple--;
        }

        System.out.println(answer);
    }
}