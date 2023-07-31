/*
  [BOJ] 9251_LCS_골드5
  https://www.acmicpc.net/problem/9251
*/

const solution = (input) => {
  const N = input[0].length;
  const M = input[1].length;
  const LCS = Array.from({ length: N + 1 }, () => Array(M + 1).fill(0));

  for (let i = 1; i <= N; i++) {
    for (let j = 1; j <= M; j++) {
      if (input[0][i - 1] !== input[1][j - 1]) {
        LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
      } else {
        LCS[i][j] = LCS[i - 1][j - 1] + 1;
      }
    }
  }

  return LCS[N][M];
};
