//package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int next, value;
    Node(int next, int value){
        this.next = next;
        this.value = value;
    }
}
public class Main {
    static int N, M, start, end;
    static ArrayList<Node>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];

        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            max = Math.max(max, c);
            min = Math.min(min, c);

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));

        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int result = 0;
        while (min <= max){
            int mid = (min + max) / 2;
            visited = new boolean[N+1];

            if(bfs(mid)){
                min = mid + 1;
                result = mid;
            }
            else{
                max = mid-1;
            }
        }
        System.out.println(result);
    }

    private static boolean bfs(int w) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()){
            int cur = q.poll();
            if(cur == end) return true;

            for(int i=0; i<list[cur].size(); i++){
                if(w <= list[cur].get(i).value && !visited[list[cur].get(i).next]){
                    visited[list[cur].get(i).next] = true;
                    q.offer(list[cur].get(i).next);
                }

            }
        }
        return false;
    }

}
