import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int M, N;
    private static long answer;
    private static int[][] map;
    private static long[][] dp;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new long[M][N];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(long[] l : dp) {
            Arrays.fill(l,-1);
        }

        long answer = dfs(0, 0);

        System.out.println(answer);
    }

    private static long dfs(int x, int y) {
        if(x == M-1 && y == N-1) {
            return 1;   //dp 배열에 경로 하나 더 추가
        }

        dp[x][y] = 0;
        
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && ny >= 0 && nx < M && ny < N && map[x][y] > map[nx][ny]) {
                //한번도 거친 곳이 아니라면
                if(dp[nx][ny] == -1) {
                    dp[x][y] += dfs(nx, ny);
                }
                // 거쳤었던 곳이라면
                else {
                    dp[x][y] += dp[nx][ny];
                }
            }
        }

        return dp[x][y];
    }
}