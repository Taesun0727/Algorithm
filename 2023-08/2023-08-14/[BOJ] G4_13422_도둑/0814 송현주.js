/*
[BOJ] 13422_도둑_골드4
https://www.acmicpc.net/problem/13422
*/

const input = require("fs")
  .readFileSync("./input.txt")
  .toString()
  .trim()
  .split("\n");

const T = +input.shift();

for (let tc = 0; tc < T; tc++) {
  const [N, M, K] = input.shift().split(" ").map(Number);
  const houses = input.shift().split(" ").map(Number);

  let answer = 0;
  let left = 0;
  let right = M - 1;
  let sum = 0;

  // 연속된 M개의 합
  for (let i = left; i <= right; i++) {
    sum += houses[i];
  }

  // 연속된 M개의 집이 전체라면 1
  if (N === M) {
    if (sum < K) console.log(1);
    else console.log(0);
    continue;
  }

  while (left < N) {
    // 연속된 M개의 집의 돈의 합이 K보다 작다면 카운트
    if (sum < K) {
      answer++;
    }

    // 포인터 이동 및 합 조정
    sum -= houses[left];
    left += 1;
    right += 1;

    // 오른쪽 맨 끝이라면 다시 인덱스 0으로 변경
    if (right === N) right = 0;
    sum += houses[right];
  }

  console.log(answer);
}
