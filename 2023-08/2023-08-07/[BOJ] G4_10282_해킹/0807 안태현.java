import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int[] cost;
    private static boolean[] isVisited;
    private static ArrayList<Computer>[] list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int INF = Integer.MAX_VALUE;

        StringTokenizer st;
        for(int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());   // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken());   // 의존성 개수
            int c = Integer.parseInt(st.nextToken());   // 해킹당한 컴퓨터

            list = new ArrayList[n+1];
            isVisited = new boolean[n+1];
            cost = new int[n+1];

            // 배열 초기화
            for(int i = 1; i <= n; i++) {
                cost[i] = INF;
                list[i] = new ArrayList<>();
            }

            for(int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());   // 컴퓨터 a가
                int b = Integer.parseInt(st.nextToken());   // 컴퓨터 b를 의존하며
                int s = Integer.parseInt(st.nextToken());   // s초 후 감염

                list[b].add(new Computer(a, s));
            }

            dijkstra(c);

            int infectedComputer = 0;
            int totalTime = 0;
            for(int i = 1; i <= n; i++) {
                // 감염된 컴퓨터라면
                if(cost[i] != INF) {
                    infectedComputer++;
                    totalTime = Math.max(totalTime, cost[i]);
                }

            }

            sb.append(infectedComputer).append(" ").append(totalTime).append("\n");

        }   // 테스트케이스 끝

        System.out.print(sb);

    }

    private static void dijkstra(int start) {

        PriorityQueue<Computer> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        cost[start] = 0;
        pq.offer(new Computer(start, 0));

        while(!pq.isEmpty()) {
            int cur = pq.poll().num; // 현재 컴퓨터의 번호

            if(!isVisited[cur]) {
                isVisited[cur] = true;

                // cur 컴퓨터를 의존하고 있는 모든 컴퓨터 탐색
                for(Computer next : list[cur]) {
                    // 의존하고 있는 컴퓨터의 현재 값이 더 크다면 바꾸고 q에 추가
                    if(cost[next.num] > cost[cur] + next.time) {
                        cost[next.num] = cost[cur] + next.time;
                        pq.offer(new Computer(next.num, cost[next.num]));
                    }
                }

            }
        }
    }
}

class Computer {
    int num;    // list[i]에서 i번째 컴퓨터를 의존하는 depend 컴퓨터
    int time;   // i번째 컴퓨터가 컴퓨터가 감염된 후 몇 초 후에 감염되는지

    Computer(int num, int time) {
        this.num = num;
        this.time = time;
    }

}