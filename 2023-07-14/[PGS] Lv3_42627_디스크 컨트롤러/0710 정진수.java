package Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 0710 정진수 {
    public int solution(int[][] jobs) {
        int answer = 0;
        int count = 0;
        int end = 0;
        int jobsIndex = 0;

        Arrays.sort(jobs, (o1, o2)-> o1[0]-o2[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1]-o2[1];
            }
        });

        while(count < jobs.length){
            while(jobsIndex < jobs.length && jobs[jobsIndex][0] <= end){
                pq.add(jobs[jobsIndex++]);
            }

            if(pq.isEmpty()){
                end = jobs[jobsIndex][0];
            }
            else{
                int[] temp = pq.poll();
                answer += temp[1] + end - temp[0];
                end += temp[1];
                count++;
            }
        }

        return answer / jobs.length;
    }
}
