import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static int N;

    // dp[i][0]: 루트노드가 얼리어답터가 아닐때 총 얼리어답터의 수, dp[i][1]: 루트노드가 얼리어답터일 때 총 얼리어답터의 수
    private static int[][] dp;
    private static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 친구 트리의 정점 개수
        dp = new int[N+1][2];

        list = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++) list[i] = new ArrayList<>();

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }

        search(1, -1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }

    private static void search(int cur, int parent) {

        dp[cur][0] = 0; // 얼리어답터가 아님
        dp[cur][1] = 1; // 얼리어답터임

        // 현재 노드와 이어져있는 노드들 탐색
        for(int next : list[cur]) {
            // next와 parent가 같으면 cur은 리프 노드임
            if(next != parent) {
                search(next, cur);
                // 부모노드가 얼리어답터가 아니라면 자식노드는 얼리어답터여야만 함
                // (자식노드가 얼리어답터가 아니라고 해서 부모노드가 얼리어답터여야만 한 것은 아님)
                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }

    }
}