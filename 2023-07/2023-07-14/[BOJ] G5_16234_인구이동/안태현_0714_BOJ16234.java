import java.io.*;
import java.util.*;

public class Main {

    private static int[][] map;
    private static boolean[][] visited;
    private static boolean isPossible;
    private static int N, L, R, answer;
    private static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            isPossible = false;
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) bfs(i, j);
                }
            }

            if(!isPossible) break;

            answer++;
        }

        System.out.println(answer);

    }

    private static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        ArrayList<int[]> unions = new ArrayList<>();

        queue.add(new int[]{r, c});
        unions.add(new int[]{r, c});
        int population = map[r][c]; // 연합국의 총 인구 수
        visited[r][c] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if(nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
                    int diff = Math.abs(map[cur[0]][cur[1]] - map[nr][nc]);
                    if(diff >= L && diff <= R) {
                        visited[nr][nc] = true;
                        queue.add(new int[] {nr, nc});  // 다음으로 탐색할 큐에 추가
                        unions.add(new int[] {nr, nc}); // 연합국에 추가
                        population += map[nr][nc];
                    }
                }
            }
        }

        if(unions.size() > 1) {
            isPossible = true;
            // 연합한 국가에 인구수 분배
            int each = population / (unions.size());
            for(int[] rc : unions) {
                map[rc[0]][rc[1]] = each;
            }
        }
    }

}