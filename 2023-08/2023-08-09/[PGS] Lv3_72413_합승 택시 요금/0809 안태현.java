class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int INF = Integer.MAX_VALUE;
        
        int[][] costs = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) costs[i][j] = 0;
                else costs[i][j] = INF;
            }
        }
        
        for(int[] f : fares) {
            costs[f[0]][f[1]] = costs[f[1]][f[0]] = f[2];
        }
        
        // 플로이드 워셜
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(costs[i][k] != INF && costs[k][j] != INF)
                        costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            int sum = costs[s][i] + costs[i][a] + costs[i][b];
            System.out.println(sum);
            answer = Math.min(answer, sum);
        }
        
        return answer;
        
    }
    
}