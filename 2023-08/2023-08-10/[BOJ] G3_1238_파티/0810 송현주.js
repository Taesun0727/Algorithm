/*
[BOJ] 1238_파티_골드3
https://www.acmicpc.net/problem/1238
*/

const input = require("fs")
  .readFileSync("./input.txt")
  .toString()
  .trim()
  .split("\n");

const [N, M, X] = input[0].split(" ").map(Number);
const graph = Array.from({ length: N + 1 }, () => []);
for (let i = 1; i <= M; i++) {
  const [from, to, cost] = input[i].split(" ").map(Number);
  graph[from].push([to, cost]);
}

const dijkstra = (start, end) => {
  const distance = Array(N + 1).fill(Infinity);
  const pq = [[start, 0]];
  distance[start] = 0;

  while (pq.length) {
    const [v, cost] = pq.shift();
    if (distance[v] < cost) continue;

    for (let next of graph[v]) {
      const moveCost = cost + next[1];
      if (moveCost < distance[next[0]]) {
        distance[next[0]] = moveCost;
        pq.push([next[0], moveCost]);
      }
    }
  }

  return distance[end];
};

let answer = 0;
for (let home = 1; home <= N; home++) {
  answer = Math.max(answer, dijkstra(X, home) + dijkstra(home, X));
}

console.log(answer);
