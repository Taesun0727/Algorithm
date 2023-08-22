import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int[] parents;
    private static int[][] godInfo;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        godInfo = new int[N][2];

        parents = new int[N];
        for(int i = 0; i < N; i++) {
            parents[i] = i;
        }

        // 우주신들 좌표 받기
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            godInfo[i] = new int[] {x, y};
        }

        // 우주신 연결
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int god1 = Integer.parseInt(st.nextToken()) - 1;
            int god2 = Integer.parseInt(st.nextToken()) - 1;

            union(god1, god2);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.distance, o2.distance));
        for(int i = 0; i < N; i++) {
            int[] god1 = godInfo[i];
            for(int j = 0; j < N; j++) {
                int[] god2 = godInfo[j];

                if(i == j) continue;

                pq.add(new Edge(i, j, getDistance(god1, god2)));
            }
        }

        double answer = 0;
        while(!pq.isEmpty()) {

            Edge cur = pq.poll();

            // 두 정점의 부모가 같지 않다면 이어주고 같은 부모를 갖게 해주기
            if(find(cur.start) != find(cur.end)) {
                answer += cur.distance;
                union(cur.start, cur.end);
            }

        }

        System.out.printf("%.2f", answer);

    }

    private static double getDistance(int[] g1, int[] g2) {
        return Math.sqrt(Math.pow(g1[0] - g2[0], 2) + Math.pow(g1[1] - g2[1], 2));
    }

    private static int find(int god) {
        if(parents[god] == god) return god;
        else return parents[god] = find(parents[god]);
    }

    private static void union(int god1, int god2) {
        god1 = find(god1);
        god2 = find(god2);

        if(god1 < god2) parents[god2] = god1;
        else parents[god1] = god2;
    }

    private static class Edge {
        int start;
        int end;
        double distance;

        Edge(int start, int end, double distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

}