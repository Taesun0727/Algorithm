import java.util.*;

class Solution {
    
    private static int[] parents;   // 부모 인덱스 저장
    
    public int solution(int n, int[][] costs) {
        
        int answer = 0;
        
        parents = new int[n];
        for(int i = 0; i < n; i++) {
            parents[i] = i; // 일단 자기 자신으로 세팅
        }     
        
        // 건설 비용 순으로 정렬
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        for(int i = 0; i < costs.length; i++) {
            // 다리를 연결 지을 두 섬이 같은 부모를 갖지 않았다면
            if(find(costs[i][0]) != find(costs[i][1])) {
                answer += costs[i][2];  // 다리 만들기
                union(costs[i][0], costs[i][1]);    // 두 섬을 한 그룹으로 묶기
            }
        }
        
        return answer;
    }
    
    // 같은 부모를 가졌다면 합치기
    private static void union(int island1, int island2) {
        int parent1 = find(island1);
        int parent2 = find(island2);
        
        parents[parent1] = parent2;
    }
    
    // island 번호를 가진 섬의 부모 섬 찾기
    private static int find(int island) {
        if(parents[island] == island) return island;
        else return find(parents[island]);
    }
    
}