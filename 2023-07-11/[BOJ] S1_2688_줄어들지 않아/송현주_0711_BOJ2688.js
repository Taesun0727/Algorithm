/* 
[BOJ] 2688_줄어들지 않아_실버1
https://www.acmicpc.net/problem/2688
*/

const solution = (input) => {
  let answer = "";
  const [T, ...numbers] = input.map((line) => parseInt(line));
  const dp = Array.from({ length: 10 }, () => Array(65).fill(1));

  for (let i = 1; i < 10; i++) {
    dp[i][1] = 1;
  }

  for (let digit = 1; digit < 65; digit++) {
    for (let num = 1; num < 10; num++) {
      for (let k = 1; k <= digit; k++) {
        dp[num][digit] += dp[num - 1][k];
      }
    }
  }

  for (let i = 0; i < T; i++) {
    answer += dp[9][numbers[i]] + "\n";
  }
  return answer.trimEnd();
};
