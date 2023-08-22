import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {


    private static PriorityQueue<Integer> negative;
    private static PriorityQueue<Integer> positive;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //책의 개수
        int M = Integer.parseInt(st.nextToken());   //한번에 들 수 있는 책의 개수

        int stepCount = 0;
        st = new StringTokenizer(br.readLine());

        //한 쪽 방향을 모두 정리하고 다음 방향을 정리하는 것이 최소걸음으로 가능
        negative = new PriorityQueue<>(Collections.reverseOrder());
        positive = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < N ; i++) {
            int input = Integer.parseInt(st.nextToken());
            if(input > 0) positive.add(input);
            else {
                input = -input;
                negative.add(input);
            }
        }

        //가장 먼 곳은 한번만 가기 위해(0으로 돌아올 필요 없으므로) 가장 먼곳 먼저 제외
        if(negative.isEmpty()) {
            stepCount += positive.poll();
            for (int i = 1; i < M; i++) {
                if(!positive.isEmpty()) {
                    positive.poll();
                } else break;
            }
        }
        else if(positive.isEmpty()) {
            stepCount += negative.poll();
            for (int i = 1; i < M; i++) {
                if(!negative.isEmpty()) {
                    negative.poll();
                } else break;
            }
        }
        else if(negative.peek() > positive.peek()) {
            stepCount += negative.poll();
            for (int i = 1; i < M; i++) {
                if(!negative.isEmpty()) {
                    negative.poll();
                } else break;
            }
        } else {
            stepCount += positive.poll();
            for (int i = 1; i < M; i++) {
                if(!positive.isEmpty()) {
                    positive.poll();
                } else break;
            }
        }

        //남은 책들 정리. 0으로 왕복해야하므로 2배 적용
        while(!positive.isEmpty()) {
            stepCount += positive.poll() * 2;
                for (int i = 1; i < M; i++) {
                    if(!positive.isEmpty()) {
                        positive.poll();
                    } else break;
                }
        }
        while(!negative.isEmpty()) {
            stepCount += negative.poll() * 2;
            for (int i = 1; i < M; i++) {
                if(!negative.isEmpty()) {
                    negative.poll();
                } else break;
            }
        }

        System.out.println(stepCount);

    }

}