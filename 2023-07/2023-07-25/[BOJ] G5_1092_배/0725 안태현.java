import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Integer[] cranes = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }


        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> boxes = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        // 내림차순으로 크레인, 상자 정렬
        Arrays.sort(cranes, Collections.reverseOrder());
        Collections.sort(boxes, Collections.reverseOrder());

        int time = 0;

        // 박스를 옮길 수 없는 경우
        if(boxes.get(0) > cranes[0]) {
            time = -1;
        }
        else {
            while(!boxes.isEmpty()) {
                for(int i = 0; i < cranes.length; i++) {
                    int boxesLength = boxes.size();
                    for(int j = 0; j < boxesLength; j++) {
                        if(cranes[i] >= boxes.get(j)) {
                            boxes.remove(j);
                            break;
                        }
                    }
                }
                time++;
            }
        }

        System.out.println(time);
    }
}