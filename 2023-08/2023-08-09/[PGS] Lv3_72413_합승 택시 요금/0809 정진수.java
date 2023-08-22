//package ShortestRoute;

public class Solution {
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 100000000;
        int[][] dist = new int[n+1][n+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i == j) dist[i][j] = 0;
                else dist[i][j] = 100000000;
            }
        }

        for(int i=0; i<fares.length; i++){
            dist[fares[i][0]][fares[i][1]] = dist[fares[i][1]][fares[i][0]] = fares[i][2];
        }

        for(int k=1; k<= n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(dist[i][k] != 100000000 && dist[k][j] != 100000000) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        for(int i=1; i<=n; i++){
            int cost = dist[s][i] + dist[i][a] + dist[i][b];
            answer = Math.min(cost, answer);
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 6, s = 4, a = 6, b =2;
        int[][] fa = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(solution(n, s, a, b, fa));
    }
}
