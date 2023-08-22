//package ShortestRoute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Party {
    int next, value;
    public Party(int next, int value){
        this.next = next;
        this.value = value;
    }
}
public class Main {
    static int N, M, X;
    static ArrayList<Party>[] list;
    static ArrayList<Party>[] reverse_list;
    static int[] dist;
    static int[] reverse_dist;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //학생 수
        M = Integer.parseInt(st.nextToken());   //단방향 도로 수
        X = Integer.parseInt(st.nextToken());   //모일 마을 번호

        dist = new int[N+1];
        reverse_dist = new int[N+1];
        Arrays.fill(dist, 1000000000);
        Arrays.fill(reverse_dist, 1000000000);

        list = new ArrayList[N+1];
        reverse_list = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<>();
            reverse_list[i] = new ArrayList<>();
        }


        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Party(b, c));
            reverse_list[b].add(new Party(a, c));
        }   //입력완료


        dijkstra(list, dist, X);
        dijkstra(reverse_list, reverse_dist, X);

        result = -1;
        for(int i=1; i<=N; i++){
            result = Math.max(result, dist[i] + reverse_dist[i]);
        }
        System.out.println(result);

    }

    private static void dijkstra(ArrayList<Party>[] list, int[] dist, int start) {
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Party> q = new PriorityQueue<>(new Comparator<Party>() {
            @Override
            public int compare(Party o1, Party o2) {
                return o1.value - o2.value;
            }
        });

        q.add(new Party(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()){
            Party cur = q.poll();

            if(visited[cur.next]) continue;
            visited[cur.next] = true;

            for(int i=0; i<list[cur.next].size(); i++){
                int cost = dist[cur.next] + list[cur.next].get(i).value;

                if(cost < dist[list[cur.next].get(i).next]){
                    dist[list[cur.next].get(i).next] = cost;
                    q.add(new Party(list[cur.next].get(i).next, cost));
                }
            }

        }
    }

}
