import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int answer = 0;
        for(int i = 0; i < N; i++) {

            int left = 0;
            int right = N-1;

            while(left < right) {

                if(left == i) left++;
                if(right == i) right--;

                if(left >= right) break;

                int sum = nums[left] + nums[right];

                if(sum > nums[i]) right--;
                else if(sum < nums[i]) left++;
                else {
                    answer++;
                    break;
                }

            }

        }

        System.out.println(answer);

    }
}