import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int INF = Integer.MAX_VALUE;

        int numTests = Integer.parseInt(br.readLine());
        for (int t = 0; t < numTests; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] dist = new int[N+1][N+1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = INF;
                }
            }

            for (int i = 1; i <= N; i++) {
                dist[i][i] = 0;
            }

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                dist[a][b] = cost;
                dist[b][a] = cost;
            }

            int friends = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] rooms = new int[friends];
            for (int i = 0; i < friends; i++) {
                rooms[i] = Integer.parseInt(st.nextToken());
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if(dist[i][k] != INF && dist[k][j] != INF)
                            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }

            int[] result = new int[N+1];
            result[0] = INF;

            for (int room : rooms) {
                for (int j = 1; j <= N; j++) {
                    result[j] += dist[room][j];
                }
            }

            int minIndex = 1;
            for (int i = 2; i <= N; i++) {
                if (result[i] < result[minIndex]) {
                    minIndex = i;
                }
            }

            sb.append(minIndex).append("\n");
        }

        System.out.print(sb);
    }

}