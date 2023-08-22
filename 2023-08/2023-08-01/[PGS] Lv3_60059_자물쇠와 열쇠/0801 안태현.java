import java.util.*;

class Solution {
    
    private static int M, N;
    
    public boolean solution(int[][] key, int[][] lock) {
        
        M = key.length;
        N = lock.length;
        
        // key의 가장자리가 Lock의 가장자리에 만나면서 탐색할 수 있게끔 largeLock 생성
        int[][] largeLock = new int[N+(M*2)-2][N+(M*2)-2];
        for(int[] line : largeLock) {
            Arrays.fill(line, -1);
        }
        // 가운데에 원래 lock 배열 넣어주기
        int r = M-1;
        for(int i = 0; i < N; i++) {
            int c = M-1;
            for(int j = 0; j < N; j++) {
                largeLock[r][c] = lock[i][j];
                c++;
            }
            r++;
        }
        
        boolean answer = false;
        
        // lock에 겹치게 탐색
        for(int i = 0; i <= largeLock.length-M; i++) {
            for(int j = 0; j <= largeLock.length-M ; j++) {
                
                // Key 배열을 4번 회전시키기
                for(int d = 0; d < 4; d++) {
                    
                    key = rotate(key);
                    
                    // largeLock 복사
                    int[][] largeLockCopy = new int[largeLock.length][largeLock.length];
                    for(int a = 0; a < largeLockCopy.length; a++) {
                        for(int b = 0; b < largeLockCopy.length; b++) {
                            largeLockCopy[a][b] = largeLock[a][b];
                        }
                    }
                    
                    
                    // key 배열만큼 탐색
                    boolean isPossible = true;
                    for(int k = 0; k < M; k++) {
                        for(int l = 0; l < M; l++) {

                            // Lock은 홈이고 key는 돌기일 경우 Lock의 홈 채우기
                            if(largeLockCopy[i+k][j+l] == 0 && key[k][l] == 1) {
                                largeLockCopy[i+k][j+l] = 1;
                            }
                            // 돌기와 돌기가 만났다면 이 위치의 현재key는 불가능
                            else if(largeLockCopy[i+k][j+l] == 1 && key[k][l] == 1) {
                                isPossible = false;
                            }

                        }
                    }
                    
                    //돌기랑 돌기가 만나지 않으면서 Lock이 다채워졌는가?
                    if(isPossible && isKey(largeLockCopy)) return true;
                    
                }
                
            }
        }
        
        return false;
        
    }
    
    private static int[][] rotate(int[][] arr) {
        int[][] rotatedArr = new int[M][M];
        int c = M-1;
        for(int i = 0; i < M; i++) {
            int r = 0;
            for(int j = 0; j < M; j++) {
                rotatedArr[r][c] = arr[i][j];
                r++;
            }
            c--;
        }
        
        return rotatedArr;
    }
    
    private static boolean isKey(int[][] arr) {
        for(int i = M-1; i < arr.length-(M-1); i++) {
            for(int j = M-1; j < arr.length-(M-1); j++) {
                if(arr[i][j] == 0) return false;
            }
        }
        return true;
    }
    
}