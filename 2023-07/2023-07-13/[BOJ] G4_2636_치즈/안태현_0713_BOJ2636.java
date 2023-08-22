import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int[][] cheeses;
    private static int X, Y;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static Queue<int[]> queue = new LinkedList<int[]>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        X = Integer.parseInt(st.nextToken());   //가로 줄 개수
        Y = Integer.parseInt(st.nextToken());   //세로 줄 개수

        cheeses = new int[X][Y];

        for (int i = 0; i < X; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < Y; j++) {
                cheeses[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int hours = 0;
        int count = -1;

        cheeses[0][0] = -1; // 0,0은 항상 외부 공기
        bfs(0 ,0);

        while(count == -1) {

            hours++;

            for(int i = 1; i < X - 1; i++) {
                for(int j = 1; j < Y - 1; j++) {

                    //치즈라면 주위에 외부공기(-1이 있는지 확인)
                    if(cheeses[i][j] == 1) {
                        for(int k = 0; k < 4; k++) {
                            int ni = i + dx[k];
                            int nj = j + dy[k];
                            if(cheeses[ni][nj] == -1) {
                                cheeses[i][j] = 2; // 녹을 예정
                                break;
                            }
                        }
                    }
                }
            }
            count = countAndMelt();
        }

        sb.append(hours).append("\n").append(count);
        System.out.println(sb);
    }

    private static int countAndMelt() {

        int oneHourAgo = 0;
        int blank = 0;

        for(int i = 0; i < X; i++) {
            for(int j = 0; j < Y; j++) {

                // 녹아야할 치즈를 녹이면서 녹은 자리를 기준으로 외부공기 갱신
                if(cheeses[i][j] == 2) {
                    cheeses[i][j] = -1;
                    oneHourAgo++;
                    bfs(i, j);
                }

                // 치즈가 없는 자리 세기
                if(cheeses[i][j] == -1) blank++;
            }
        }

        // 모든 칸이 빈칸이 되었다면 직전 치즈 개수 리턴
        if(blank == X * Y) return oneHourAgo;
        // 아직 치즈가 남아있다면 -1을 리턴하여 계속 진행시키기
        else return -1;
    }

    // bfs를 이용하여 (startX, startY) 좌표를 기준으로 외부공기 체크하기
    private static void bfs(int startX, int startY) {
        queue.add(new int[] {startX, startY});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx >= 0 && ny >= 0 && nx < X && ny < Y) {
                    if(cheeses[nx][ny] == 0) {
                        queue.add(new int[] {nx, ny});
                        cheeses[nx][ny] = -1;
                    }
                }
            }
        }
    }
}
