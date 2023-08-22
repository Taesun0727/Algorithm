import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = Integer.MAX_VALUE;
        int N = Integer.parseInt(st.nextToken());
        int[] snows = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            snows[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snows);

        // elsa의 눈사람을 먼저 만든 후에
        // 투포인터를 통해 anna의 눈사람 크기 구축
        for(int elsa1 = 0; elsa1 < N-1; elsa1++) {
            for(int elsa2 = elsa1+1; elsa2 < N; elsa2++) {

                int elsaSnowman = snows[elsa1] + snows[elsa2];
                int anna1 = 0;
                int anna2 = N-1;

                while(anna1 < anna2) {

                    // 안나와 엘사는 같은 눈덩이를 고를 수 없으므로 같아진다면 포인터 이동
                    if(anna1 == elsa1 || anna1 == elsa2) {
                        anna1++;
                        continue;
                    } else if(anna2 == elsa1 || anna2 == elsa2) {
                        anna2--;
                        continue;
                    }

                    int annaSnowman = snows[anna1] + snows[anna2];
                    answer = Math.min(answer, Math.abs(elsaSnowman - annaSnowman));

                    if(elsaSnowman == annaSnowman) break;   // 가장 작은 차(0)
                    else if(elsaSnowman > annaSnowman) {
                        anna1++;
                    }
                    else {
                        anna2--;
                    }

                }

            }
        }

        System.out.println(answer);

    }

}