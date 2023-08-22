import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, answer;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1 , 0 , 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                dfs(i, j, 1, map[i][j]);
                convex(i, j);
            }
        }

        System.out.println(answer);

    }

    // ㅜ 모양 제외하고 체크
    private static void dfs(int startX, int startY, int cnt, int sum) {
        if(cnt == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        visited[startX][startY] = true;

        for(int i = 0; i < 4; i++) {
            int nx = startX + dx[i];
            int ny = startY + dy[i];

            if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                dfs(nx, ny, cnt+1, sum + map[nx][ny]);
            }
        }

        visited[startX][startY] = false;

    }

    private static void convex(int startX, int startY) {
        // ㅓ 모양
        if(startX <= N - 3 && startY >= 1) {
            int sum = map[startX][startY] + map[startX + 1][startY] + map[startX + 2][startY] + map[startX + 1][startY - 1];
            answer = Math.max(answer, sum);
        }

        // ㅏ 모양
        if(startX <= N - 3 && startY <= M - 2) {
            int sum = map[startX][startY] + map[startX + 1][startY] + map[startX + 2][startY] + map[startX + 1][startY + 1];
            answer = Math.max(answer, sum);

        }

        // ㅗ 모양
        if(startX >= 1 && startY <= M - 3) {
            int sum = map[startX][startY] + map[startX][startY + 1] + map[startX][startY + 2] + map[startX - 1][startY + 1];
            answer = Math.max(answer, sum);
        }

        // ㅜ 모양
        if(startX <= N - 2 && startY <= M - 3) {
            int sum = map[startX][startY] + map[startX][startY + 1] + map[startX][startY + 2] + map[startX + 1][startY + 1];
            answer = Math.max(answer, sum);
        }
    }
}