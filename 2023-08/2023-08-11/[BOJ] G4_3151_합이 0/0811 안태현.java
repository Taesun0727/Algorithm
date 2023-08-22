import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] abilities = new int[N];
        long answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            abilities[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(abilities);

        // 한 명을 고정시켜놓고 투포인터 실행
        for(int i = 0; i < N; i++) {

            // 고른 수가 0 이상이라면 후에 고를 수도 양수이므로 0만들기 불가
            if(abilities[i] > 0) break;

            int left = i+1;
            int right = N-1;

            while(left < right) {

                if(right >= N) break;

                int sum = abilities[left] + abilities[right] + abilities[i];

                if(sum > 0) right--;
                else if(sum < 0) left++;
                    // 0이 되었다면
                else {
                    int l = 1;
                    int r = 1;

                    // left의 수가 몇번 연속됐는지 구하기
                    if(left + l < N) {
                        while(abilities[left] == abilities[left+l]) {
                            l++;
                            if(left+l >= N) break;
                        }
                    }

                    // right의 수가 몇번 연속됐는지 구하기
                    if(right - r > i) {
                        while(abilities[right] == abilities[right-r]) {
                            r++;
                            if(right-r <= i) break;
                        }
                    }

                    // 연속된게 있다면
                    if(l != 1 || r != 1) {
                        // left에 있는 수와 right에 있는 수가 동일하다면
                        if(abilities[left] == abilities[right]) {
                            answer += (l * (l-1)) / 2;
                            break;
                        }
                        // 다른 두 수로 이루어진것이라면
                        else {
                            answer += l * r;
                        }

                        left += l;
                        right -= r;
                    }
                    // 연속된게 없다면
                    else {
                        answer++;
                        left++;
                        right--;
                    }

                }

            }
        }

        System.out.println(answer);

    }
}