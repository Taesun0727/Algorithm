import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Character> brokenBtn;

    public static void main(String[] args) throws IOException {

        int answer = Integer.MAX_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int channel = Integer.parseInt(br.readLine());
        int brokenCnt = Integer.parseInt(br.readLine());
        brokenBtn = new ArrayList<>();

        if(brokenCnt == 0) {
            int cnt = Math.abs(100 - channel);
            answer = Math.min(cnt, String.valueOf(channel).length());
            System.out.println(answer);
            System.exit(0);
        }

        String input = br.readLine();
        String[] inputArray = input.split(" ");
        for(int i = 0; i < brokenCnt; i++) {
            brokenBtn.add(inputArray[i].charAt(0));
        }

        // 보려는 채널을 만들 수 있는지
        if(isPossible(channel)) {
            // 100에서 올리거나 내리는게 더 나은지
            // 직접 치는게 더 나은지
            answer = Math.min(Math.abs(100 - channel), String.valueOf(channel).length());
            System.out.println(answer);
            System.exit(0);
        }

        // 보려는 채널 번호의 이하의 수에서 가능한 번호 찾기.
        int curChannel = channel;
        int cnt = 0;
        while(true) {
            cnt++;
            if(isPossible(--curChannel)) {
                cnt += String.valueOf(curChannel).length();
                answer = Math.min(answer, cnt);
                break;
            }
            if(curChannel < 0) break;
        }

        // 보려는 채널 번호의 이상의 수에서 가능한 번호 찾기.
        curChannel = channel;
        cnt = 0;
        while(true) {
            cnt++;
            if(isPossible(++curChannel)) {
                cnt += String.valueOf(curChannel).length();
                answer = Math.min(answer, cnt);
                break;
            }
            if(curChannel > 999999) break;
        }


        // 근데 100번에서 그냥 올렸다 내렸다 하는게 더 버튼 조작 횟수가 적다면
        cnt = Math.abs(100-channel);
        answer = Math.min(answer, cnt);

        System.out.println(answer);

    }

    private static boolean isPossible(int num) {

        String stringNum = String.valueOf(num);
        for(int i = 0; i < stringNum.length(); i++) {
            if(brokenBtn.contains(stringNum.charAt(i))) return false;
        }

        return true;
    }
}