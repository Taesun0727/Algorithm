//package ShortestRoute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class virus{
    int move, cnt;
    public virus(int move, int cnt){
        this.move = move;
        this.cnt = cnt;
    }
}
public class Main {
    static int T;
    static ArrayList<ArrayList<virus>> list;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   //컴퓨터 개수
            int d = Integer.parseInt(st.nextToken());   //의존성 개수
            int c = Integer.parseInt(st.nextToken());   //해킹당한 컴퓨터 번호

            list = new ArrayList<>();
            for(int k=0; k<=n; k++){
                list.add(new ArrayList<>());
            }
            dist = new int[n+1];

            Arrays.fill(dist, (int)1e9);

            for(int j=0; j<d; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list.get(b).add(new virus(a, s));

            }
            dijkstra(c);

            int cnt_com = 0;
            int cnt_time = 0;
            for(int j=1; j<=n; j++){
                if(dist[j] != (int)1e9){
                    cnt_com++;
                    cnt_time = Math.max(cnt_time, dist[j]);
                }
            }

            System.out.println(cnt_com+" "+cnt_time);
        }

    }

    private static void dijkstra(int start) {
        PriorityQueue<virus> q = new PriorityQueue<>(new Comparator<virus>() {
            @Override
            public int compare(virus o1, virus o2) {
                return o1.cnt- o2.cnt;
            }
        });

        q.add(new virus(start, 0));
        dist[start] = 0;

        while (!q.isEmpty()){
            virus cur = q.poll();


            if(dist[cur.move] < cur.cnt) continue;

            for(int i=0; i<list.get(cur.move).size(); i++){
                int cost = dist[cur.move] + list.get(cur.move).get(i).cnt;

                if(cost < dist[list.get(cur.move).get(i).move]){
                    dist[list.get(cur.move).get(i).move] = cost;
                    q.add(new virus(list.get(cur.move).get(i).move, cost));
                }
            }

        }
    }


}
