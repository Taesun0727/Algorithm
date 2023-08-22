/*
[BOJ] 13424_비밀모임_골드4
https://www.acmicpc.net/problem/13424
*/

const input = require("fs")
  .readFileSync("./input.txt")
  .toString()
  .trim()
  .split("\n");

const T = +input.shift();

for (let tc = 1; tc <= T; tc++) {
  const [N, M] = input.shift().split(" ").map(Number);
  const distance = Array.from({ length: N + 1 }, () =>
    Array(N + 1).fill(Infinity)
  );
  for (let i = 0; i < M; i++) {
    const [from, to, cost] = input[i].split(" ").map(Number);
    distance[from][to] = cost;
    distance[to][from] = cost;
  }

  const K = input[M];
  const friends = input[M + 1].split(" ").map(Number);

  input.splice(0, M + 2);

  for (let i = 1; i <= N; i++) {
    distance[i][i] = 0;
  }

  for (let k = 1; k <= N; k++) {
    for (let i = 1; i <= N; i++) {
      for (let j = 1; j <= N; j++) {
        if (distance[i][k] + distance[k][j] < distance[i][j]) {
          distance[i][j] = distance[i][k] + distance[k][j];
        }
      }
    }
  }

  let room = -1;
  let answer = Number.MAX_SAFE_INTEGER;
  for (let i = 1; i <= N; i++) {
    let sum = 0;
    for (let friend of friends) {
      sum += distance[i][friend];
    }

    if (sum < answer) {
      answer = sum;
      room = i;
    }
  }
  console.log(room);
}
