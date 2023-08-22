import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        HashMap<Long, Integer> map = new HashMap<>();   // key: 실제 인덱스, value: 모기 수

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long entranceIndex = Long.parseLong(st.nextToken());
            long exitIndex = Long.parseLong(st.nextToken());
            map.put(entranceIndex, map.getOrDefault(entranceIndex, 0) + 1);
            map.put(exitIndex, map.getOrDefault(exitIndex, 0) - 1);

            // 0이 되었으면 hashmap에서 제거
            if(map.get(entranceIndex) == 0) map.remove(entranceIndex);
            if(map.get(exitIndex) == 0) map.remove(exitIndex);
        }

        // Hashmap을 index(key)의 오름차순으로 정렬
        ArrayList<Long> indexList = new ArrayList<>(map.keySet());
        Collections.sort(indexList);

        int[] timeline = new int[map.size()];
        for(int i = 0; i < map.size(); i++) {
            timeline[i] = map.get(indexList.get(i));
        }

        for(int i = 1; i < timeline.length; i++) {
            timeline[i] = timeline[i-1] + timeline[i];
        }

        int max = -1;
        int start = 0;
        int end = 0;
        for(int i = 0; i < timeline.length; i++) {
            if(timeline[i] > max) {
                max = timeline[i];
                start = i;
                end = i+1;
                while(i+1 < timeline.length && timeline[i] == timeline[i+1]) {
                    i++;
                    end++;
                }
            }
        }

        if(end > indexList.size()) end = indexList.size() - 1;

        StringBuilder sb = new StringBuilder();
        sb.append(timeline[start]).append("\n");
        sb.append(indexList.get(start)).append(" ").append(indexList.get(end));

        System.out.println(sb);

    }

}