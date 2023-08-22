//package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N;
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            N = Integer.parseInt(br.readLine());

            visited = new boolean[N+1];
            parent = new int[N+1];

            for(int i=1; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                parent[b] = a;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            check(s, e);
        }
    }

    private static void check(int s, int e) {
        while (s > 0){
            visited[s] = true;
            s = parent[s];
        }

        while (e > 0){
            if(visited[e]){
                System.out.println(e);
                break;
            }
            e = parent[e];
        }
    }
}
