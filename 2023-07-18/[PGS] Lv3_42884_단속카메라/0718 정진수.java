package Greedy;

import java.util.Arrays;

public class Main {
    public int solution(int[][] routes) {
        int answer = 0;
        int cnt = 0;

        //배열 정렬(끝 지점으로 정렬)
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);

        // 비교값 초기화
        int tmp = Integer.MIN_VALUE;

        //첫 번째 배열부터 route[i][0] ~ route[i][1]안에 다른 배열이 포함 확인
        for(int[] arr : routes){
            if(tmp < arr[0]){
                tmp = arr[1];
                answer++;
            }
        }


        return answer;
    }
}
