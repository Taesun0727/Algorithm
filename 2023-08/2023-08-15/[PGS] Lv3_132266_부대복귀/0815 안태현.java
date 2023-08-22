import java.util.*;

class Solution {
    
    private static int[] times;
    private static boolean[] isVisited;
    private static ArrayList<Node>[] list;
    private static int INF = Integer.MAX_VALUE;

    public static int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        list = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int[] r : roads) {
            list[r[0]].add(new Node(r[1], 1));
            list[r[1]].add(new Node(r[0], 1));
        }
         
        isVisited = new boolean[n+1];
        times = new int[n+1];
        Arrays.fill(times, INF);
        
        dijkstra(destination);
                           
        for(int i = 0; i < sources.length; i++) {
            int from = sources[i];
            answer[i] = times[from] == INF ? -1 : times[from];
        }
                           
        return answer;
    }

    private static void dijkstra(int destination) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        pq.add(new Node(destination, 0));
        times[destination] = 0;
        
        while(!pq.isEmpty()) {
            
            Node cur = pq.poll();
            
            if(!isVisited[cur.num]) {
                
                isVisited[cur.num] = true;
                
                for(int i = 0; i < list[cur.num].size(); i++) {
                    Node next = list[cur.num].get(i);
                    
                     if(next.time != INF && times[next.num] > cur.time + next.time) {
                             int sum = cur.time + next.time;
                             times[next.num] = sum;
                             pq.add(new Node(next.num, sum));
                     }
                }
                
            }
            
        }
        
    }
    
    private static class Node {
        int num;
        int time;
        
        Node(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
    
              
              
              
              
              
}