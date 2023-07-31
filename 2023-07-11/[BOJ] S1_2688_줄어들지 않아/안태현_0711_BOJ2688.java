import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 안태현_0711_BOJ2688 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        long[][] dp = new long[65][10]; // 1자리 수 ~ 64자리 수, 숫자는 0~9 가능

        //1자리 수가 가능한 숫자 개수들 저장
        Arrays.fill(dp[1], 1);  //한자리 수이면서 dp[1][i]인 숫자로 끝나는 수의 개수는 모두 1개씩

        //1자리 수는 모두 저장했으니까 2자리부터 64자리까지 저장하기
        for(int i = 2; i <= 64; i++) {
            for(int j = 0; j <= 9; j++) {
                for(int k = j; k <= 9; k++) {
                    dp[i][j] += dp[i-1][k];
                }
            }
        }

        for(int tc = 0; tc < T; tc++) {

            int n = Integer.parseInt(br.readLine());
            long count = 0;

            for (int i = 0; i <= 9; i++) {
                count += dp[n][i];
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);

    }

}