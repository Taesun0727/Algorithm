import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[][] map = new int[9][9];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        for(int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
    }

    private static void dfs(int r, int c) {

        // 해당 열의 끝에 왔으면 다음 행 탐색
        if(c == 9) {
            dfs(r+1, 0);
            return;
        }

        // 탐색 끝났으면 종료시키기
        if(r == 9) {
            print();
            System.exit(0);
        }

        // 빈 칸이라면
        if(map[r][c] == 0) {
            for(int i = 1; i < 10; i++) {
                if(isPossible(r, c, i)) {
                    map[r][c] = i;
                    dfs(r, c+1);
                }
            }
            map[r][c] = 0;
            return;
        }

        dfs(r, c+1);

    }

    private static boolean isPossible(int r, int c, int num) {

        // 행 탐색
        for(int i = 0; i < 9; i++) {
            if(num == map[r][i]) return false;
        }

        // 열 탐색
        for(int i = 0; i < 9; i++) {
            if(num == map[i][c]) return false;
        }

        // 네모 탐색
        int nr = r / 3 * 3;
        int nc = c / 3 * 3;
        for(int i = nr; i < nr + 3; i++) {
            for(int j = nc; j < nc + 3; j++) {
                if(num == map[i][j]) return false;
            }
        }

        return true;

    }

    private static void print() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}