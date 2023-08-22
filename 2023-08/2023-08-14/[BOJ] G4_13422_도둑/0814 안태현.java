import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for(int tc = 0; tc < T; tc++) {

            st = new StringTokenizer(br.readLine());

            int answer = 0;
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

                int[] houses = new int[N + M - 1];

                st = new StringTokenizer(br.readLine());

                for (int i = 0; i < N; i++) {
                    houses[i] = Integer.parseInt(st.nextToken());
                }

                if(N == M) {
                    int money = 0;
                    for(int i = 0; i < M; i++) {
                        money += houses[i];
                    }
                    if (money < K) answer++;
                }
                else{
                    // 추가로 집 짓기
                    int first = 0;
                    for (int i = N; i < N + M - 1; i++) {
                        houses[i] = houses[first++];
                    }

                    int money = 0;
                    for (int i = 0; i < M; i++) {
                        money += houses[i];
                    }
                    if (money < K) answer++;

                    for (int i = 0, j = i + M; j < N + M - 1; i++, j++) {
                        money = money - houses[i] + houses[j];
                        if (money < K) answer++;
                    }
                }

            sb.append(answer).append("\n");

        }

        System.out.println(sb);

    }
}