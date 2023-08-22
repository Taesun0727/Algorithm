import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, M;
    private static char[][] map;
    private static int[][] groups;  // 해당 칸의 그룹번호
    private static int groupNum = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        groups = new int[N][M];

        for(int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                // 그룹이 정해지지 않은 위치라면 dfs 탐색
                if(groups[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }

        System.out.println(--groupNum);
    }

    private static int dfs(int r, int c) {
        // 같은 그룹을 만났다면
        if(groups[r][c] == groupNum) {
            groupNum++; // 그룹 개수 증가
            return -1;
        }
        // 다른 그룹을 만났다면
        else if(groups[r][c] != 0) {
            return groups[r][c];
        }

        groups[r][c] = groupNum;

        // DLRU에 맞춰 이동
        int nr = r;
        int nc = c;
        switch (map[r][c]) {
            case 'D':
                nr += 1;
                break;
            case 'U':
                nr -= 1;
                break;
            case 'R':
                nc += 1;
                break;
            case 'L':
                nc -= 1;
                break;
            default: System.exit(-1);
        }

        int parentGroupNum = dfs(nr, nc);

        if(parentGroupNum != -1) groups[r][c] = parentGroupNum;

        return parentGroupNum;
    }

}