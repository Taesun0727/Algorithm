import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int N, X, INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 학생 수(마을 수) (1 <= N <= 1,000)
        int M = Integer.parseInt(st.nextToken());   // 도로 개수 (1 <= M <= 10,000)
        X = Integer.parseInt(st.nextToken());   // 파티하는 마을 번호 (1 <= X <= N)

        ArrayList<Town>[] goParty = new ArrayList[N+1];   // i -> X로 가는 경우
        ArrayList<Town>[] goHome = new ArrayList[N+1];    // X -> i로 가는 경우
        for(int i = 1; i < N+1; i++) {
            goParty[i] = new ArrayList<>();
            goHome[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            // X마을에서 i마을로 가는 경우의 도로 상태
            goHome[from].add(new Town(to, time));
            // i마을에서 X마을로 가는 경우의 도로 상태
            // (X를 출발점으로 잡고 다익스트라를 돌리기 위해서 도로의 방향을 반대로 설정)
            goParty[to].add(new Town(from, time));

        }

        int[] goPartyTime = dijkstra(goParty);
        int[] goHomeTime = dijkstra(goHome);

        int answer = -1;
        for(int i = 1; i < N+1; i++) {
            answer = Math.max(answer, goPartyTime[i] + goHomeTime[i]);
        }

        System.out.println(answer);

    }

    private static int[] dijkstra(ArrayList<Town>[] list) {

        PriorityQueue<Town> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        pq.offer(new Town(X, 0));

        int[] times = new int[N+1];
        Arrays.fill(times, INF);
        times[X] = 0;

        boolean[] isVisited = new boolean[N+1];

        while(!pq.isEmpty()) {

            Town cur = pq.poll();

            if(!isVisited[cur.to]) {

                isVisited[cur.to] = true;

                for(Town next : list[cur.to]) {
                    if(!isVisited[next.to] && times[next.to] > times[cur.to] + next.time) {
                        times[next.to] = times[cur.to] + next.time;
                        pq.add(new Town(next.to, times[next.to]));
                    }
                }

            }
        }

        return times;
    }

}

class Town {
    int to; // 이 마을에 연결된 도로는 어디로 가는지
    int time;   // 이 마을에 연결된 도로를 가는데 소요 되는 시간

    Town(int to, int time) {
        this.to = to;
        this.time = time;
    }
}