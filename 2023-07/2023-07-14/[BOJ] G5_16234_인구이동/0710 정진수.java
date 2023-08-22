package DFSBFS;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 0710 정진수 {
    static int N, L, R;
    static int[][] map;
    static boolean flag;
    static int result;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];


        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }   //입력완료

        while (true){
            visited = new boolean[N][N];
            flag = false;

            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(!visited[i][j]) bfs(i, j);
                }
            }

            if(!flag) break;

            result++;
        }
        System.out.println(result);
    }

    private static void bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        ArrayList<Point> list = new ArrayList<>();

        q.add(new Point(r, c));
        list.add(new Point(r, c));
        visited[r][c] = true;

        while (!q.isEmpty()){
            Point cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            for(int k=0; k<4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && Math.abs(map[x][y]-map[nx][ny]) >= L &&
                Math.abs(map[x][y]-map[nx][ny]) <= R) {
                    q.add(new Point(nx, ny));
                    list.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        if(list.size() > 1){
            flag = true;
        }

        int value = 0;
        for(int i=0; i<list.size(); i++){
            value += map[list.get(i).x][list.get(i).y];
        }

        int p = value/list.size();

        for(int i=0; i<list.size(); i++){
            map[list.get(i).x][list.get(i).y] = p;
        }
    }
}
