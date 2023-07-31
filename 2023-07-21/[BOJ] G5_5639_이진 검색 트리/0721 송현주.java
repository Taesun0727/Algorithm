public class Solution5639 {
    static class Node {
        int value;
        Node left, right;
        public Node(int value) {
            this.value = value;
        }
        // 노드 연결을 위한 메서드
        public void connectNode(int otherValue) {
            if(otherValue < this.value) {
                if(this.left == null) this.left = new Node(otherValue);
                else this.left.connectNode(otherValue);
            }else {
                if(this.right == null) this.right = new Node(otherValue);
                else this.right.connectNode(otherValue);
            }
        }
    }
    
    static StringBuilder answer; // 정답
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node rootNode = new Node(Integer.parseInt(br.readLine()));
        while (true) {
            String input = br.readLine();
            if (input == null || input.equals("")) break;
            rootNode.connectNode(Integer.parseInt(input));
        }
        answer = new StringBuilder();
        handlePostOrder(rootNode);
        System.out.println(answer);
    }

    public static void handlePostOrder(Node node) {
        if(node == null) return;
        handlePostOrder(node.left);
        handlePostOrder(node.right);
        answer.append(node.value).append("\n");
    }
}
