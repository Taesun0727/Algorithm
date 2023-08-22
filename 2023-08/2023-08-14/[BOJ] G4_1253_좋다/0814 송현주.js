/*
[BOJ] 1253_좋다_골드4
https://www.acmicpc.net/problem/1253
*/

const input = require("fs")
  .readFileSync("./input.txt")
  .toString()
  .trim()
  .split("\n");

const N = +input[0];
const numbers = input[1].split(" ").map(Number);
numbers.sort((a, b) => a - b);
let answer = 0;

for (let idx = 0; idx < N; idx++) {
  const current = numbers[idx];
  let left = 0;
  let right = N - 1;

  while (left < right) {
    let sum = numbers[left] + numbers[right];

    if (sum === current) {
      if (idx !== left && idx !== right) {
        answer += 1;
        break;
      }
      idx === right ? (right -= 1) : (left += 1);
      continue;
    }
    sum < current ? (left += 1) : (right -= 1);
  }
}
console.log(answer);
