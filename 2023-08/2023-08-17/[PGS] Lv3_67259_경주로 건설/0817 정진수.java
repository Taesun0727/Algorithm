//package DFSBFS;


import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    static class Route{
        int x, y, cost, dir;
        Route(int x, int y, int cost, int dir){
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }

    static boolean[][][] visited;
    static int result;
    static int N;

    //하우상좌
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static int solution(int[][] board) {
        int answer = 0;
        N = board.length;

        visited = new boolean[N][N][4];
        result = Integer.MAX_VALUE;

        bfs(0, 0, board);

        answer = result;

        return answer;
    }

    private static void bfs(int r, int c, int[][] board) {
        Queue<Route> q = new ArrayDeque<>();

        q.add(new Route(r, c, 0, -1));
        visited[r][c][0] = visited[r][c][1] = visited[r][c][2] = visited[r][c][3] = true;

        while (!q.isEmpty()){
            Route cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int money = cur.cost;
            int d = cur.dir;

            if(x == N-1 && y == N-1) {
                result = Math.min(result, money);
            }

            for(int k=0; k<4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx <0 || ny < 0 || nx >= N || ny >= N || board[nx][ny] == 1) continue;

                int nMoney = money;
                if(d == -1 || d == k) nMoney += 100;
                else nMoney += 600;

                if(!visited[nx][ny][k] || board[nx][ny] >= nMoney){
                    visited[nx][ny][k] = true;
                    board[nx][ny] = nMoney;
                    q.add(new Route(nx, ny, nMoney, k));
                }
            }
        }

    }


    public static void main(String[] args) {
        int[][] board1 = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};
        int[][] board4 = {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};

        System.out.println(solution(board1));
        System.out.println(solution(board4));
    }
}
