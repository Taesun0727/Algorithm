//package Greedy;

import java.util.*;

public class Solution {
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Node>[] list;
    static class Node{
        int next, d;

        Node(int next, int d){
            this.next = next;
            this.d = d;
        }
    }

    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        list = new ArrayList[n+1];

        for(int i=1; i<n+1; i++){
            list[i] = new ArrayList<>();
        }

        visited = new boolean[n+1];
        dist = new int[n+1];
        Arrays.fill(dist, 1000000);

        for(int i=0; i<roads.length; i++){
            list[roads[i][0]].add(new Node(roads[i][1], 1));
            list[roads[i][1]].add(new Node(roads[i][0], 1));
        }

        dijkstra(destination);

        for(int i=0; i<sources.length; i++){
            int start = sources[i];

            if(dist[start] != 1000000) answer[i] = dist[start];
            else answer[i] = -1;
        }
        return answer;
    }

    private static void dijkstra(int destination) {

        PriorityQueue<Node> q = new PriorityQueue<>(((o1, o2) -> o1.d - o2.d));

        q.add(new Node(destination, 0));
        dist[destination] = 0;

        while (!q.isEmpty()){
            Node cur = q.poll();

            if(!visited[cur.next]){
                visited[cur.next] = true;

                for(int i =0; i<list[cur.next].size(); i++){
                    Node tmp = list[cur.next].get(i);

                    if(tmp.d != 1000000 && dist[tmp.next] > cur.d + tmp.d){
                        int sum = cur.d + tmp.d;
                        dist[tmp.next] = sum;
                        q.add(new Node(tmp.next, sum));
                    }
                }
            }

        }
    }
}
