class Solution {
    // skill = {{type, r1, c1, r2, c2, degree}}
    public int solution(int[][] board, int[][] skills) {
        
        int r = board.length;
        int c = board[0].length;
        
        // 누적합을 만들 배열
        int[][] sum = new int[r+1][c+1];
        
        // 누적합 이전 세팅 ex. {0, 0, 0, -3, 0, 0, 0, 3, 0}
        for(int[] skill : skills) {
            
            int destroy = 1;
            // 파괴라면
            if(skill[0] == 1) destroy = -1;
            
            int power = destroy * skill[5];
            
            // 누적합 시작좌표에 저장
            sum[skill[1]][skill[2]] += power;
            
            // 누적합 원상 복귀 시킬 좌표 (r의 최대)
            sum[skill[3]+1][skill[2]] += power * -1;
            
            // 누적합 원상 복귀 시킬 좌표 (c의 최대)
            sum[skill[1]][skill[4]+1] += power * -1;
            
            // 누적합 원상 복귀 시킬 좌표 (r과 c의 최대)
            sum[skill[3]+1][skill[4]+1] += power;
            
        }
        
        // 누적합 만들기
        // 열마다 더하기
        for(int j = 0; j < c; j++) {
            for(int i = 0; i < r; i++) {
                sum[i+1][j] += sum[i][j];
            }
        }
        // 행마다 더하기
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                sum[i][j+1] += sum[i][j];
            }
        }
        
        // sum 배열과 board를 합치면서 파괴되지 않은 건물(0 이상) 찾기
        int answer = 0;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                int building = board[i][j] + sum[i][j];
                if(building > 0) answer++;
            }
        }
        
        return answer;
    }
}