import java.io.*;
import java.util.*;

public class Main {

    private static int N, minLength = Integer.MAX_VALUE;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs 탐색을 통해서 섬에 번호 부여 (2, 3, 4, ...) - 2부터 부여
        int islandNumber = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    classifyIsland(i, j, islandNumber);
                    islandNumber++;
                }
            }
        }

        //모서리들의 좌표를 저장할 리스트
        List<List<int[]>> moseoriList = new ArrayList<>();

        // 섬 개수만큼 돌면서 모서리를 찾아서 List에 저장
        for (int num = 2; num < islandNumber; num++) {
            List<int[]> innerList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    //모서리인지 확인하고 모서리라면 list에 추가
                    if (map[i][j] == num && isMoseori(i, j)) {
                        innerList.add(new int[]{i, j});
                    }
                }
            }
            moseoriList.add(innerList);
        }

        //각 리스트를 돌면서 최소 거리 찾기
        for (int i = 0; i < moseoriList.size() - 1; i++) {
            for (int j = i + 1; j < moseoriList.size(); j++) {

                List<int[]> isLandInnerList1 = moseoriList.get(i);
                List<int[]> isLandInnerList2 = moseoriList.get(j);

                for (int k = 0; k < isLandInnerList1.size(); k++) {
                    for (int l = 0; l < isLandInnerList2.size(); l++) {
                        int distance = getDistance(isLandInnerList1.get(k), isLandInnerList2.get(l));
                        minLength = Math.min(minLength, distance);
                    }
                }

            }
        }

        System.out.println(minLength);

    }

    //bfs를 통해서 각 섬에 고유번호(2부터) 부여
    private static void classifyIsland(int r, int c, int islandNumber) {

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        map[r][c] = islandNumber;
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
                    q.offer(new int[]{nr, nc});
                    map[nr][nc] = islandNumber;
                    visited[nr][nc] = true;
                }
            }
        }
    }

    //각 점 사이의 거리 구하기
    private static int getDistance(int[] a1, int[] a2) {
        return Math.abs(a1[0] - a2[0]) + Math.abs(a1[1] - a2[1]) - 1;
    }

    private static boolean isMoseori(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            //바다랑 인접해있으면 모서리임
            if (isIn(nr, nc) && map[nr][nc] == 0) {
                return true;
            }
        }
        return false;
    }

    // 허용범위 이내인지 확인
    private static boolean isIn(int r, int c) {
        if (r >= 0 && c >= 0 && r < N && c < N) return true;
        else return false;
    }
}