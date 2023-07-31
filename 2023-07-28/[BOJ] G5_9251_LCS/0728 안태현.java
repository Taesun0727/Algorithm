import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = Integer.MIN_VALUE;
        String s1 = br.readLine();
        String s2 = br.readLine();

        int[][] LCS = new int[s1.length()+1][s2.length()+1];
        Arrays.fill(LCS[0], 0);
        for(int i = 0; i < s1.length(); i++) LCS[i][0] = 0;

        for(int i = 1; i < s1.length()+1; i++) {
            for(int j = 1; j < s2.length()+1; j++) {
                
                if(s1.charAt(i-1) == s2.charAt(j-1)) LCS[i][j] = LCS[i-1][j-1] + 1;
                else LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);

                answer = Math.max(answer, LCS[i][j]);
            }
        }

        System.out.println(answer);

    }
    
}