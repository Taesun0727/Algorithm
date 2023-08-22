import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] board = new char[3][3];

        while(true) {
            String input = br.readLine();
            if(input.equals("end")) break;

            int xCnt = 0, oCnt = 0;

            for(int i = 0; i < 9; i++) {
                char c = input.charAt(i);
                board[i/3][i%3] = c;
                if(c == 'O') oCnt++;
                else if(c == 'X') xCnt++;
            }

            // x의 개수가 2 이하거나 oCnt가 더 크거나 xCnt와의 차이가 2 이상이라면 불가능
            if(xCnt <= 2 || oCnt > xCnt || xCnt-oCnt >= 2) {
                sb.append("invalid").append("\n");
                continue;
            }

            // 무승부: D, 승자 없음: -
            char winner = whoWin(board);

            // 무승부는 있을 수 없음
            if(winner == 'D') {
                sb.append("invalid").append("\n");
                continue;
            }

            // 승부가 나지 않았는데 빈칸이 있을 경우
            if(winner == '-' && xCnt + oCnt != 9) {
                sb.append("invalid").append("\n");
                continue;
            }

            // X가 이겼는데 O의 개수와 X의 개수가 같을 경우
            if(winner == 'X' && xCnt == oCnt) {
                sb.append("invalid").append("\n");
                continue;
            }

            // X의 개수가 더 많을 경우 O는 이길 수 없음
            if(xCnt > oCnt) {
                if(winner == 'O') {
                    sb.append("invalid").append("\n");
                    continue;
                }
            }

            sb.append("valid").append("\n");

        }

        System.out.println(sb);

    }

    // 누가 승자인지 판단하는 메소드 (무승부: D, 승자 없음: -)
    private static char whoWin(char[][] board) {

        // 승자가 정해지지 않았을 경우에는 -로 표현
        char winner = '-';
        boolean hasWinner;

        // 가로로 탐색
        for(int i = 0; i < 3; i++) {
            if(board[i][0] == '.') continue;
            hasWinner = true;
            for(int j = 1; j < 3; j++) {
                if(board[i][0] != board[i][j]) {
                    hasWinner = false;
                    break;
                }
            }
            if(hasWinner) {
                // winner가 이미 있고 그 winner가 이겼다고 새롭게 판단된 사람이 아니라면 draw임.
                if(winner != '-' && winner != board[i][0]) return 'D';
                else winner = board[i][0];
            }
        }

        // 세로로 탐색
        for(int j = 0; j < 3; j++) {
            if(board[0][j] == '.') continue;
            hasWinner = true;
            for(int i = 1; i < 3; i++) {
                if(board[0][j] != board[i][j]) {
                    hasWinner = false;
                    break;
                }
            }
            if(hasWinner) {
                if(winner != '-' && winner != board[0][j]) return 'D';
                else winner = board[0][j];
            }
        }

        // 대각선 탐색
        if(board[1][1] != '.'
                && ((board[1][1] == board[0][0] && board[1][1] == board[2][2])
                || (board[1][1] == board[0][2] && board[1][1] == board[2][0]))) {
            if(winner != '-' && winner != board[1][1]) return 'D';
            else winner = board[1][1];
        }

        return winner;
    }

}