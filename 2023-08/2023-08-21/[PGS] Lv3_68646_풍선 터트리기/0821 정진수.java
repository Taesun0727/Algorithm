//package BruteForce;

public class Solution {
    public static int solution(int[] a) {
        int answer = 2;
        int[] left_min = new int[a.length];
        int[] right_min = new int[a.length];

        int left_value = a[0];
        int right_value = a[a.length-1];

        for(int i=1; i<a.length; i++){
            if(left_value > a[i]) left_value = a[i];
            left_min[i] = left_value;
        }

        for(int i=a.length-2; i > 0; i--){
            if(right_value > a[i]) right_value = a[i];
            right_min[i] = right_value;
        }

        if(a.length == 1) return 1;

        for(int i=1; i<=a.length-2; i++){
            if(a[i] > left_min[i] && a[i] > right_min[i]) continue;
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] test = {-16, 27, 65, -2,58,-92,-71,-68,-61,-33};

        System.out.println(solution(test));
    }
}
