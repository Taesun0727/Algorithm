import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int tc = -1;
    private static int[] team1 = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
    private static int[] team2 = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
    private static int[] answers = new int[4];
    private static int[][] scores, result;



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(++tc < 4) {

            scores = new int[6][3];
            result = new int[6][3];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 18; i++) {
                scores[i/3][i%3] = Integer.parseInt(st.nextToken());
            }

            dfs(0);

        }

        StringBuilder sb = new StringBuilder();
        for(int a : answers) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);

    }

    private static void dfs(int cnt) {

        if(answers[tc] == 1) return;

        // 15번의 경기 종료 (6C2);
        if(cnt == 15) {

            for(int i = 0; i < 6; i++) {
                for(int j = 0; j < 3; j++) {
                    if(scores[i][j] != result[i][j]) {
                        return;
                    }
                }
            }

            answers[tc] = 1;
            return;
        }

        int t1 = team1[cnt];
        int t2 = team2[cnt];

        // t1 승, t2 패
        result[t1][0]++;
        result[t2][2]++;
        dfs(cnt+1);
        result[t1][0]--;
        result[t2][2]--;

        // t1, t2 무승부
        result[t1][1]++;
        result[t2][1]++;
        dfs(cnt+1);
        result[t1][1]--;
        result[t2][1]--;

        // t1 패, t2 승
        result[t1][2]++;
        result[t2][0]++;
        dfs(cnt+1);
        result[t1][2]--;
        result[t2][0]--;

    }

}