import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static long[] info;
    private static List<Integer>[] indegrees;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        info = new long[N+1];
        indegrees = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) indegrees[i] = new ArrayList<>();

        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            String sw = st.nextToken();
            int cnt = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            indegrees[to].add(i);

            if (sw.equals("W")) cnt *= -1;
            info[i] = cnt;
        }

        System.out.println(dfs(1));

    }

    private static long dfs(int index) {
        long sheep = 0;

        // 현재 노드의 자식 노드들 탐색
        for(int next : indegrees[index]) {
            sheep += dfs(next);
        }

        // 자식노드가 없으면 실행하게 되는 부분
        // 지금 섬이 늑대이면서 양의 수보다 크다면
        if(info[index] < 0 && Math.abs(info[index]) >= sheep) return 0; // 살아남는 양은 없다.

        else return sheep + info[index];

    }

}