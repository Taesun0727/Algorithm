import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = Integer.MAX_VALUE;
        int N = Integer.parseInt(st.nextToken());
        int[][] colorCosts = new int[N+1][3];   // colorCosts[3][0]: 3번 집을 0(빨강)으로 칠하는 비용 1: 초록, 2: 파랑
        int[][] dp = new int[N+1][3];

        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                colorCosts[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int color = 0; color < 3; color++) {

            Arrays.fill(dp[1], 1000000);    // MAX_VALUE로 고르지 않은 곳을 설정하면 더했을때 int 범위를 초과함
            dp[1][color] = colorCosts[1][color];    // 색칠할 곳의 비용을 dp에 추가

            for(int i = 2; i < N+1; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + colorCosts[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + colorCosts[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + colorCosts[i][2];
            }

            for(int i = 0; i < 3; i++) {
                if(i != color) answer = Math.min(answer, dp[N][i]);
            }
        }

        System.out.println(answer);

    }
}