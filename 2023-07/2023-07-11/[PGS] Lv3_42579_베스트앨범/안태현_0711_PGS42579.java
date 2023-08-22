import java.util.*;

class Solution {
    
    public int[] solution(String[] genres, int[] plays) {
        
        HashMap<String, Integer> genresPlays = new HashMap<>();
        for(int i = 0; i < genres.length; i++) {
            
            // 장르횟수 해시맵에 장르가 이미 들어있다면
            if(genresPlays.containsKey(genres[i])) {
                genresPlays.put(genres[i], genresPlays.get(genres[i]) + plays[i]);
            }
            
            // 없다면
            else {
                genresPlays.put(genres[i], plays[i]);
            }
            
        }
        
        // 횟수의 내림차순으로 장르 목록 정렬
        List<String> genresList = new ArrayList<>(genresPlays.keySet());
        Collections.sort(genresList, (o1, o2) -> genresPlays.get(o2) - genresPlays.get(o1));
        
        List<Integer> albumList = new ArrayList<>();
        for(String genre : genresList) {
            
            HashMap<Integer, Integer> playsMap = new HashMap<>(); //{인덱스, 재생횟수}
            
            for(int i = 0; i < genres.length; i++) {
                if(genre.equals(genres[i])) {
                    playsMap.put(i, plays[i]);
                }
            }
            
            List<Integer> playsList = new ArrayList<>(playsMap.keySet());
            Collections.sort(playsList, (o1, o2) -> playsMap.get(o2) - playsMap.get(o1));    // 재생횟수 내림차순으로 정렬
            
            // 앨범에 인덱스 집어넣기
            albumList.add(playsList.get(0));
            if(playsList.size() > 1) {
                albumList.add(playsList.get(1));
            }
        }
        
        int[] answer = new int[albumList.size()];
        for(int i = 0; i < albumList.size(); i++) {
            answer[i] = albumList.get(i);
        } 
        
        return answer;
    }
}