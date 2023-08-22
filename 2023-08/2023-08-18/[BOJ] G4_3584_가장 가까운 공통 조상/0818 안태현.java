import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int tc = 0; tc < T; tc++) {

            int N = Integer.parseInt(br.readLine());

            int[] parent = new int[N+1];
            boolean[] isVisited = new boolean[N+1];

            for(int i = 0; i < N-1; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                parent[B] = A;
            }

            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            while(node1 > 0) {
                isVisited[node1] = true;
                node1 = parent[node1];
            }
            while(node2 > 0) {
                if(isVisited[node2]) {
                    sb.append(node2).append("\n");
                    break;
                }
                node2 = parent[node2];
            }
        }

        System.out.println(sb);

    }
}