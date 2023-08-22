import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] liquids = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            liquids[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquids);

        int left = 0;
        int right = N-1;
        int[] answers = new int[2];

        int minimum = Integer.MAX_VALUE;

        while(left < right) {
            int diff = Math.abs(liquids[left]+liquids[right]);

            if(minimum > diff) {
                minimum = diff;
                answers[0] = liquids[left];
                answers[1] = liquids[right];
            }

            if(liquids[left] + liquids[right] > 0) right--;
            else left++;

        }

        System.out.println(answers[0] + " " + answers[1]);

    }

}