package Greedy;


import java.util.Arrays;

// union find 서로소로 풀기
public class Solution {
    static int[] parent;

    public static int find(int x){
        if(x == parent[x]) return parent[x];
        else return parent[x] = find(parent[x]);
    }
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a > b) parent[a] = b;
        else parent[b] = a;
    }
    public int solution(int n, int[][] costs) {
        int answer = 0;

        parent = new int[n];
        for(int i=0; i<parent.length; i++){
            parent[i] = i;
        }
        Arrays.sort(costs, (int[] o1, int[] o2) -> o1[2] - o2[2]);

        for(int i=0; i<costs.length; i++){
            if(find(costs[i][0]) == find(costs[i][1])) continue;
            union(costs[i][0], costs[i][1]);
            answer += costs[i][2];
        }

        return answer;
    }
}
