/*
[BOJ] 15961_회전초밥_골드4
https://www.acmicpc.net/problem/15961
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, D, K, C;
    static ArrayList<Integer> sushiBelt;
    static int[] picked;
    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 접시의 수
        D = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        K = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        C = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        // 회전 초밥 벨트 생성
        sushiBelt = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            sushiBelt.add(Integer.parseInt(br.readLine()));
        }

        // k개 추가
        sushiBelt.addAll(sushiBelt.subList(0, K - 1));

        // 현재 선택한 K개의 초밥 수
        Deque<Integer> queue = new ArrayDeque<>();
        
        // K개만큼 초밥 선택
        int cnt = 0;
        picked = new int[D + 1];
        for(int i=0; i<K; i++) {
            int current =  sushiBelt.get(i);
            queue.add(current);
            picked[current] ++;
            if(picked[current] == 1) cnt++;
        }

        // 탐색 시작
        int answer = 0;
        int left = 0;
        int right = K - 1;

        while(left < N) {

            // 지금 순서로 먹었을 때, 몇 가지의 초밥을 먹을 수 있을지 찾기
            answer = Math.max(answer, picked[C] >= 1 ? cnt : cnt + 1);

            // 한칸씩 이동
            left ++;
            right ++;

            // 범위를 벗어나면 종료
            if(left == N) break;

            // 맨 앞 요소 제거
            int first = queue.pollFirst();

            // 제거한 초밥 카운트 줄이기, 선택한 초밥 중 중복된 초밥 없다면 cnt - 1
            picked[first] --;
            if(picked[first] == 0) cnt --;

            // 새로운 초밥 추가
            int sushi = sushiBelt.get(right);
            queue.offerLast(sushi);

            // 추가된 초밥 카운트 올리기, 중복된 초밥 없다면 cnt + 1
            picked[sushi] ++;
            if(picked[sushi] == 1) cnt ++;
        }

        System.out.println(answer);
    }
}
