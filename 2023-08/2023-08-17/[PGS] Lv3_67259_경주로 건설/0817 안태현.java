import java.util.*;

class Solution {
    
    private static int N, answer = Integer.MAX_VALUE;
    private static boolean[][][] isVisited;
    private static int[][] dp;
    private static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
    
    public int solution(int[][] board) {

        N = board.length;
        
        isVisited = new boolean[N][N][4];
        dp = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        bfs(board);
        
        return answer;
    }
    
    private static void bfs(int[][] board) {
        
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, -1, 0));
        
        while(!q.isEmpty()) {
            
            Node cur = q.poll();
            
            // 목적지에 도착하면 최소비용 갱신
            if(cur.r == N-1 && cur.c == N-1) {
                answer = Math.min(answer, cur.cost);
            }
            
            for(int d = 0; d < 4; d++) {
                
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                
                if(isIn(nr, nc) && board[nr][nc] == 0) {
                    
                    int nextCost = cur.cost;
                    
                    // 이전 방향과 같거나 처음 시작한 좌표라면 100원 소요
                    if(cur.d == d || cur.d == -1) {
                        nextCost += 100;
                    }
                    // 방향이 달라지면 500원 추가 소요돼서 600원
                    else {
                        nextCost += 600;
                    }
                    
                    // 방문하지 않았거나 지금 계산한 건설비용이 더 낮은 경우에 갱신
                    if(!isVisited[nr][nc][d] || dp[nr][nc] >= nextCost) {
                        q.add(new Node(nr, nc, d, nextCost));
                        isVisited[nr][nc][d] = true;
                        dp[nr][nc] = nextCost;
                    }
                    
                }
                
            }
            
        }
        
    }
    
    private static boolean isIn(int r, int c) {
        if(r < 0 || c < 0 || r >= N || c >= N) return false;
        return true;
    }
    
    private static class Node {
        int r, c, d, cost;
        
        Node(int r, int c, int d, int cost) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cost = cost;
        }
    }
}