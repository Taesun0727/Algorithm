package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, k;
    static int[] friend_cost;
    static int[] list;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        friend_cost = new int[N+1];
        list = new int[N+1];
        result = 0;

        for(int i=1; i<=N; i++){
            list[i] = i;
        }
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            friend_cost[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            union(list, start, end);
        }

        for(int i=1; i<=N; i++){
            if(list[i] == i) result += friend_cost[i];
        }

        if(k - result < 0) System.out.println("Oh no");
        else System.out.println(result);
    }

    private static void union(int[] list, int a, int b) {
        a = find(list, a);
        b = find(list, b);


        if(friend_cost[a] > friend_cost[b]) list[a] = b;
        else list[b] = a;

    }

    private static int find(int[] list, int x) {
        if(list[x] == x) return x;
        else return find(list, list[x]);
    }
}
