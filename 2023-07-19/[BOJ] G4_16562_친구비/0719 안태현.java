import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] friendFees, rootNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        friendFees = new int[N];

        rootNode = new int[N];
        for(int i = 0; i < N; i++) {
            rootNode[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            friendFees[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int friend1 = Integer.parseInt(st.nextToken()) - 1;
            int friend2 = Integer.parseInt(st.nextToken()) - 1;
            union(friend1, friend2);
        }

        int needMoney = 0;

        for(int i = 0; i < N; i++) {
            if(rootNode[i] == i) {
                needMoney += friendFees[i];
            }
        }

        if(needMoney > k) System.out.println("Oh no");
        else System.out.println(needMoney);

    }

    private static void union(int friend1, int friend2) {
        int root1 = find(friend1);
        int root2 = find(friend2);

        // 둘이 루트 노드가 같지 않다면
        if(root1 != root2) {
            // 친구비가 더 낮은걸로 루트 노드 설정
            if(friendFees[root1] > friendFees[root2]) {
                rootNode[root1] = root2;
            } else {
                rootNode[root2] = root1;
            }
        }
    }

    private static int find(int index) {
        // 자기 자신이 루트 노드라면 자기 자신 리턴
        if(rootNode[index] == index) return index;
        // 자기 자신이 루트 노드가 아니라면 재귀로 계속 탐색
        return rootNode[index] = find(rootNode[index]);
    }
}