import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Node root = new Node(); // 루트노드

        for(int n = 0; n < N; n++) {

            StringBuilder answer = new StringBuilder();
            String nickname = br.readLine();

            // 트라이 알고리즘
            Node node = root;

            boolean isDone = false; // 내부별칭이 정해졌는지

            for(int i = 0; i < nickname.length(); i++) {

                char curChar = nickname.charAt(i);

                // 해당 문자를 가진 노드가 있다면
                if(node.child.get(curChar) != null) {
                    // 그 노드로 이동해서 계속 내려가기 위해서 node 갱신
                    node = node.child.get(curChar);
                    answer.append(curChar);
                }
                // 없다면
                else {
                    Node next = new Node();
                    node.child.put(curChar, next);
                    node = next;
                    if(!isDone) {
                        answer.append(curChar);
                        isDone = true;
                    }
                }
            }

            node.duplicateCnt++;
            sb.append(answer);
            if(node.duplicateCnt == 1) sb.append("\n");
            else if(node.duplicateCnt != 0) sb.append(node.duplicateCnt).append("\n");

        }

        System.out.println(sb);

    }

    private static class Node {
        HashMap<Character, Node> child = new HashMap<>();
        int duplicateCnt = 0;  // 같은 내부 별칭을 갖는 별칭 뒤에 붙을 숫자
    }

}