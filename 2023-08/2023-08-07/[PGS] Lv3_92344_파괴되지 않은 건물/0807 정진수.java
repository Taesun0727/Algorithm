//package Implementation;

public class Solution {
    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;

        int[][] sum = new int[N+1][M+1];

        for(int[] k : skill){
            int option = k[0];
            int x1 = k[1], y1 = k[2];
            int x2 = k[3], y2 = k[4];
            int value = k[5];

            if(option == 1){
                sum[x1][y1] += -value;
                sum[x2+1][y1] += value;
                sum[x1][y2+1] += value;
                sum[x2+1][y2+1] += -value;

            } else {
                sum[x1][y1] += value;
                sum[x2+1][y1] += -value;
                sum[x1][y2+1] += -value;
                sum[x2+1][y2+1] += value;
            }

        }

        //누적합 좌우 계산
        for(int i=0; i<N+1; i++){
            int tmp = 0;
            for(int j=0; j<M+1; j++){
                tmp += sum[i][j];
                sum[i][j] = tmp;
            }
        }

        //누적합 상하 계산
        for(int i=0; i<M; i++){
            int tmp = 0;
            for(int j=0; j<N; j++){
                tmp += sum[j][i];
                sum[j][i] = tmp;
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(sum[i][j] + board[i][j] > 0) answer++;
            }
        }

        return answer;
    }



    public static void main(String[] args) {
        int[][] b = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] s = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};

        int[][] bo = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] sk = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};

        System.out.println(solution(bo, sk));
    }
}
