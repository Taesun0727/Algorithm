//package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Node implements Comparable<Node> {
    int w, e;
    double weight;

    Node(int w, int e, double weight){
        this.w = w;
        this.e = e;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node e){
        return Double.compare(this.weight, e.weight);
    }
}
public class Main {
    static int N, M;
    static int[][] list;
    static int[] parent;
    static double result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new int[N+1][2];
        parent = new int[N+1];


        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            union(s, e);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i=1; i<N; i++){
            for(int j=i+1; j<N+1; j++){
                int x1 = list[i][0];
                int y1 = list[i][1];
                int x2 = list[j][0];
                int y2 = list[j][1];

                double wei = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));

                pq.add(new Node(i, j, wei));
            }
        }

        result = 0;

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if(union(cur.w, cur.e)){
                result += cur.weight;
            }
        }
        System.out.println(String.format("%.2f", result));
    }

    private static boolean union(int s, int e) {
        s = find(s);
        e = find(e);

        if(s != e){
            parent[s] = e;
            return true;
        }
        return false;
    }

    private static int find(int x) {
        if(parent[x] == x){
            return x;
        }
        else {
            return parent[x] = find(parent[x]);
        }

    }
}
