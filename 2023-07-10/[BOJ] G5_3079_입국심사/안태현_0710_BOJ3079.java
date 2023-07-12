import java.util.Arrays;
import java.util.Scanner;

public class 안태현_0710_BOJ3079 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();   //심사대 수
        int M = sc.nextInt();   //친구 수

        int[] simsadae = new int[N];
        long maxTimeSimadae = -1;

        for(int i = 0; i < N; i++) {
            simsadae[i] = sc.nextInt();

            //가장 오래걸리는 심사대 찾기
            maxTimeSimadae = Math.max(simsadae[i], maxTimeSimadae);
        }
        Arrays.sort(simsadae);  //이분탐색을 위해서 오름차순 정렬

        long minTime = 0;
        long answer = Long.MAX_VALUE;

        //가장 오래걸리는 심사대로 모든 사람이 갔을 경우 걸리는 시간(최대 소요 시간)
        long maxTime = M * maxTimeSimadae;

        //이분탐색 시작
        while(minTime <= maxTime) {
            long countPerson = 0;

            long midTime = (minTime + maxTime) / 2;

            //각 입국 심사대에서 몇명을 커버할 수 있는지
            for(int time : simsadae) {
                if(countPerson >= M) break;
                countPerson += midTime / time;
            }

            if(countPerson >= M) {
                maxTime = midTime - 1;
                answer = Math.min(answer, midTime);
            } else {
                minTime = midTime + 1;
            }
        }

        System.out.println(answer);

    }
}