import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        int answer = 0;
        
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int camera = Integer.MIN_VALUE;
        
        for(int[] route : routes) {
            // 카메라의 위치가 차량의 진입 지점보다 전에 있다면
            if(camera < route[0]) {
                camera = route[1];  // 카메라의 위치를 차량의 진출 지점으로 변경하고
                answer++;   // 카메라 개수 증가
            }
        }
        
        return answer;
    }
}