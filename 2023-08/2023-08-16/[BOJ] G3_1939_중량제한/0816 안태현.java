import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N, from, to;
    private static boolean[] isVisited;
    private static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 섬의 개수
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[A].add(new Node(B, weight));
            list[B].add(new Node(A, weight));

            low = Math.min(low, weight);
            high = Math.max(high, weight);
        }

        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        int answer = 0;

        while(low <= high) {
            int middle = (low + high) / 2;

            // 가운데 값으로 도착이 가능하다면 값을 높여봄
            if(bfs(middle)) {
                low = middle+1;
                answer = middle;
            }
            else {
                high = middle-1;
            }
        }

        System.out.println(answer);

    }

    private static boolean bfs(int weight) {

        isVisited = new boolean[N+1];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(from, 0));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.to == to) return true;
            
//            isVisited[cur.to] = true;

            for(Node next : list[cur.to]) {
                if(!isVisited[next.to] && weight <= next.weight) {
                    isVisited[next.to] = true;
                    q.offer(next);
                }
            }

        }

        return false;
    }

    private static class Node {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

}