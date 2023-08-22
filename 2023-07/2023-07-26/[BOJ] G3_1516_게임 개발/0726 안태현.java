import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] answers = new int[N+1];   // i번째 건물을 짓는데에 걸리는 "총" 시간
        Arrays.fill(answers, 0);

        int[] time = new int[N+1];  // i번째 건물"만" 짓는데에 걸리는 시간
        int[] indegree = new int[N+1];    // i번째 건물을 짓기 전에 지어야하는 건물들의 개수 -> 0이 되면 i번째 건물을 세울 수 있음
        ArrayList<Integer>[] beforeList = new ArrayList[N+1];   // i번 건물을 지으면 지을 수 있는 건물의 번호들
        for(int i = 1; i < N+1; i++) beforeList[i] = new ArrayList<>();

        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            while(before != -1) {
                beforeList[before].add(i);
                indegree[i]++;
                before = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for(int i = 1; i < beforeList.length; i++) {
            if(indegree[i] == 0) {
                q.add(i); // 가장 먼저 지을 건물
                answers[i] = time[i];
            }
        }

        while(!q.isEmpty()) {

            int cur = q.poll(); // 지을 건물 번호

            // 현재 건물을 지음으로서 지을 수 있는 건물들 업데이트
            for(int i = 0; i < beforeList[cur].size(); i++) {
                int next = beforeList[cur].get(i);

                indegree[next]--;
                answers[next] = Math.max(answers[next], answers[cur] + time[next]);

                // 다음으로 지을 건물이 지을 수 있는 차례라면 q에 추가
                if(indegree[next] == 0) q.add(next);
            }

        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N+1; i++) sb.append(answers[i]).append("\n");
        System.out.print(sb);

    }

    private static void print(int[] arr) {
        for(int i = 1; i < arr.length; i++) System.out.print(arr[i] + "\t");
        System.out.println();
    }
}