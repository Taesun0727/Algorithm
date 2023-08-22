class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int intAdvTime = secTime(adv_time);
        
        int[] timeLine = new int[secTime(play_time) + 1];   // 누적합 배열
        for(String log : logs) {
            String[] splitLog = log.split("-");
            int startTime = secTime(splitLog[0]);
            int endTime = secTime(splitLog[1]);
            
            timeLine[startTime]++;
            if(endTime+1 < timeLine.length) {
                timeLine[endTime]--;
            }
        }
        
        // 누적합 실행
        for(int i = 1; i < timeLine.length; i++) {
            timeLine[i] = timeLine[i-1] + timeLine[i];
        }
        
        // 가장 많이 볼 수 있는 곳 찾기
        int maxTime = 0;
        long maxCnt = 0;
        long cnt = 0;
        for(int i = 0; i < intAdvTime; i++) {
            cnt += timeLine[i];
            maxCnt = cnt;
        }
        for(int i = 1; i < timeLine.length - intAdvTime; i++) {
            cnt = cnt - timeLine[i-1] + timeLine[i+intAdvTime-1];
            if(maxCnt < cnt) {
                maxCnt = cnt;
                maxTime = i;
            }
        }
        
        // 구한 초를 형식에 맞춰 String으로 변환
        String hours = AddZero(String.valueOf(maxTime / 3600));
        int remain = maxTime % 3600;
        String minutes = AddZero(String.valueOf(remain / 60));
        String seconds = AddZero(String.valueOf(remain % 60));
        
        return hours + ":" + minutes + ":" + seconds;
    }
        
    // HH, mm, ss 형식으로 바꿔주기 위함
    private static String AddZero(String s) {
        if(s.length() < 2) return "0" + s;
        else return s;
    }
    
    private static int secTime(String time) {
        String[] tmp = time.split(":");
        int hours = Integer.valueOf(tmp[0]) * 3600;
        int minutes = Integer.valueOf(tmp[1]) * 60;
        int seconds = Integer.valueOf(tmp[2]);
        
        return hours + minutes + seconds;
    }
}