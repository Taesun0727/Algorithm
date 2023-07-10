import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[100001];   // 1부터 100의 숫자가 몇개인지 들어갈 배열

        int maxLength = 0;
        int start = 0;
        int end = 0;

        // 지금까지 구한 최장 연속 부분 수열의 길이가 남은 길이보다 길거나 같다면 탐색 중지
        while(end < N || maxLength < N - start) {

            //숫자의 개수 증가
            count[arr[end]]++;

            //특정한 정수의 개수가 K개 초과라면
            if(count[arr[end]] > K) {
                //해당 정수가 나오는 곳까지 start를 이동시키기
                for(int i = start; i < end; i++) {
                    if(arr[end] == arr[i]) {
                        start = i+1;
                        break;
                    }
                }
            }

            maxLength = Math.max(maxLength, end - start + 1);

            end++;
        }

        System.out.println(maxLength);

    }
}