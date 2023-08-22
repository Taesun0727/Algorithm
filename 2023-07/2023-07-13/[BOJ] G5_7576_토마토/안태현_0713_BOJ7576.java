import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int M, N;
    private static int[] dm = {-1, 0, 1, 0};
    private static int[] dn = {0, 1, 0, -1};
    private static int[][] tomatoes;
    private static Queue<int[]> queue = new LinkedList<int[]>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());   // 상자의 가로 칸 수
        N = Integer.parseInt(st.nextToken());   // 상자의 세로 칸 수

        tomatoes = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if(tomatoes[i][j] == 1) queue.add(new int[] {i, j});
            }
        }

        bfs();
        int answer = findAnswer();

        System.out.println(answer);
    }

    private static void bfs() {

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int n = cur[0] + dn[i];
                int m = cur[1] + dm[i];

                //범위 안에 있고
                if (m >= 0 && n >= 0 && m < M && n < N) {
                    //안익은 토마토(0)가 있는 자리라면
                    if (tomatoes[n][m] == 0) {
                        queue.add(new int[]{n, m});
                        tomatoes[n][m] = tomatoes[cur[0]][cur[1]] + 1;
                    }
                    //토마토가 있긴 있는데
                    else if (tomatoes[n][m] > 0) {
                        // 저장되어 있는 날짜 수가 현재 날짜 경과 보다 크다면 교체
                        if (tomatoes[n][m] > tomatoes[cur[0]][cur[1]] + 1) {
                            queue.add(new int[]{n, m});
                            tomatoes[n][m] = tomatoes[cur[0]][cur[1]] + 1;
                        }
                    }
                }
            }
        }
    }

    private static int findAnswer() {

        int minValue = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(tomatoes[i][j] == 0) return -1;
                else {
                    minValue = Math.max(minValue, tomatoes[i][j]);
                }
            }
        }
        return minValue-1;
    }

}