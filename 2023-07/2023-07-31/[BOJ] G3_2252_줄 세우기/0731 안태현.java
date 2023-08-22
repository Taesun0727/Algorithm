import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indegrees = new int[N+1];  // i번 학생 앞으로 몇 명이 있는지
        ArrayList<Integer>[] list = new ArrayList[N+1]; // i번째 학생을 세우면 어떠한 학생들이 설 수 있는지
        for(int i = 1; i < N+1; i++) list[i] = new ArrayList<>();


        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            indegrees[second]++;
            list[first].add(second);

        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i < N+1; i++) {
            // 자신보다 앞에 설 학생이 없다면 q에 추가
            if(indegrees[i] == 0) {
                q.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {

            int cur = q.poll();
            sb.append(cur + " ");

            for(int next : list[cur]) {
                indegrees[next]--;
                if(indegrees[next] == 0) q.offer(next);
            }

        }

        System.out.println(sb);

    }

}