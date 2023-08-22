import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    private static int N;
    private static int[] parents;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int have = 0;   // 원래 랜선을 얼마나 갖고 있는지

        parents = new int[N];
        for(int i = 0; i < N; i++) {
            parents[i] = i;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.length - o2.length);

        for(int i = 0; i < N; i++) {
            String inputString = br.readLine();
            for(int j = 0; j < N; j++) {
                char inputChar = inputString.charAt(j);
                int length = Integer.valueOf(inputChar);

                // 입력이 0이 아니라면 (컴퓨터끼리 연결되어있다면)
                if(length != 48) {
                    // 소문자일 경우
                    if(length >= 96) {
                        length -= 96;
                    }
                    // 대문자일 경우
                    else if(length >= 65){
                        length -= 38;
                    }

                    pq.add(new Node(i, j, length));
                }

                // 입력이 0이면
                else {
                    length = 0;
                }

                have += length; // 현재 가지고 있는 총 랜선 길이

            }
        }

        // 탐색 시작
        int need = 0;   // 필요한 랜선의 길이
        int cnt = 0;    // 랜선의 개수
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int com1 = cur.com1;
            int com2 = cur.com2;

            // 부모가 같다면 연결되어 있으므로 연결할 필요 없음
            if(find(com1) == find(com2)) continue;

            cnt++;
            need += cur.length;
            union(com1, com2);
        }

        // 컴퓨터를 모두 잇기위한 최소 랜선 개수(N-1)와 같다면
        if(cnt == N-1) {
            System.out.println(have - need);
        }
        else {
            System.out.println(-1);
        }

    }

    private static int find(int com1) {
        if(parents[com1] == com1) {
            return com1;
        }
        return parents[com1] = find(parents[com1]);
    }

    private static void union(int com1, int com2) {
        com1 = find(com1);
        com2 = find(com2);

        if(com1 < com2) parents[com2] = com1;
        else parents[com1] = com2;
    }

    private static class Node {
        int com1;
        int com2;
        int length;

        Node(int com1, int com2, int length) {
            this.com1 = com1;
            this.com2 = com2;
            this.length = length;
        }
    }

}