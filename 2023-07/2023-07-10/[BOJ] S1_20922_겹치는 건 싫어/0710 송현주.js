/* 
[BOJ] 20922_겹치는건 싫어_실버 1
https://www.acmicpc.net/problem/20922
*/

const solution = (input) => {
  const numMap = {};
  const [[N, K], numbers] = input.map((line) =>
    line.split(" ").map((num) => parseInt(num))
  );

  let answer = 0;
  let left = 0;
  let right = 0;

  while (left <= right && right < N) {
    while (numMap[numbers[right]] === K) {
      numMap[numbers[left]] -= 1;
      left += 1;
    }
    answer = Math.max(answer, right - left + 1);
    numMap[numbers[right]] = !numMap[numbers[right]]
      ? 1
      : numMap[numbers[right]] + 1;
    right++;
  }
  return answer;
};

const input = [];
const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.on("line", (line) => {
  input.push(line);
  if (input.length == 2) {
    console.log(solution(input));
  }
});
