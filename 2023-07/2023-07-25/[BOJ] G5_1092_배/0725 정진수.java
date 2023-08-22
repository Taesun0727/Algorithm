package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] crain;
    static int[] box;
    static ArrayList<Integer> check;
    static int time_result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());    //크레인 갯수
        crain = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            crain[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());    //박스 갯수
        box = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            box[i] = Integer.parseInt(st.nextToken());
        }   //입력 완료

        // 박스 옮겼는지 판별하기 위한 리스트 생성
        check = new ArrayList<>();
        // 시간 초기화
        time_result = 0;

        // 크레인 무게 제한, 박스 무게 오름차순 정렬
        Arrays.sort(crain);
        Arrays.sort(box);

        for(int i=0; i<box.length; i++){
            check.add(box[i]);
        }

        if(crain[crain.length-1] < box[box.length-1]) System.out.println(-1);
        else{
            while (!check.isEmpty()){
                for(int i=crain.length-1; i>=0; i--){
                    for(int j=check.size()-1; j>=0; j--){
                        if(crain[i] >= check.get(j)) {
                            check.remove(j);
                            break;
                        }
                    }

                }
                time_result++;
            }
            System.out.println(time_result);
        }


    }
}
