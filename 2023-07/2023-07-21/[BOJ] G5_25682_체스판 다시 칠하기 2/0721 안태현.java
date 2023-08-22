import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, M, K, answer = Integer.MAX_VALUE;
    private static boolean[][] map; //false: 검정색, true: 흰색

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M; j++) {
                if(input.charAt(j) == 'W') map[i][j] = true;
            }
        }

        changeColor(true);  // 좌측 최상단을 "흰색"으로 만들어서 체크
        changeColor(false); // 좌측 최상단을 "검정색"으로 만들어서 체크

        System.out.println(answer);
    }

    private static void changeColor(boolean isWhite) {
        boolean[][] cloneMap = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            cloneMap[i] = map[i].clone();
        }
        boolean[][] isChanged = new boolean[N][M];

        boolean haveTo = isWhite;   // 칸에 있어야하는 색깔
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(cloneMap[i][j] != haveTo) {
                    cloneMap[i][j] = haveTo;
                    isChanged[i][j] = true;
                }
                haveTo = !haveTo;
            }
            haveTo = !cloneMap[i][0];
        }
        prefixSum(isChanged);
    }

    // 누적합 배열 만들기
    private static void prefixSum(boolean[][] isChanged) {

        int[][] sum = new int[N][M];
        if(isChanged[0][0]) sum[0][0] = 1;
        else sum[0][0] = 0;

        // 첫번째 행 채우기
        for(int j = 1; j < M; j++) {
            sum[0][j] = sum[0][j-1];
            if(isChanged[0][j])
                sum[0][j]++;
        }

        // 첫번째 열 채우기
        for(int i = 1; i < N; i++) {
            sum[i][0] = sum[i-1][0];
            if(isChanged[i][0])
                sum[i][0]++;
        }

        //두번째 행부터 채우기
        for(int i = 1; i < N; i++) {
            for(int j = 1; j < M; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
                if(isChanged[i][j]) sum[i][j]++;
            }
        }

        getMin(sum);
    }

    private static void getMin(int[][] sum) {
        int cnt = 0;
        for(int i = K-1; i < N; i++) {
            for(int j = K-1; j < M; j++) {

                int leftCnt = 0;    // 왼쪽 범위의 개수
                int upCnt = 0;      // 위쪽 범위의 개수
                int leftUpCnt = 0;  // 왼쪽 대각선 범위의 개수

                if(j-K >= 0) leftCnt = sum[i][j-K];
                if(i-K >= 0) upCnt = sum[i-K][j];
                if(i-K >= 0 && j-K >= 0) leftUpCnt = sum[i-K][j-K];

                cnt = sum[i][j] - leftCnt - upCnt + leftUpCnt;

                answer = Math.min(answer, cnt);
            }
        }

    }
}