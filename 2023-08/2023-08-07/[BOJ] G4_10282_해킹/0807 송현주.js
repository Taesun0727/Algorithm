/*
[BOJ] 10282_해킹_골드4
https://www.acmicpc.net/problem/10282 
*/

const INF = 1e9; // 10억
const solution = (N, graph, S) => {
  const distance = Array(N + 1).fill(INF);
  let pq = [];

  // 해킹이 시작된 컴퓨터 정보
  pq.push([S, 0]);
  distance[S] = 0;

  while (pq.length) {
    const [num, time] = pq.shift(); // 우선순위 큐에 저장되어 있는 정보 하나 제거

    // 이미 거리가 갱신되었다면 더 이상 갱신 x
    if (distance[num] < time) continue;

    for (let next of graph[num]) {
      const nextTime = time + next[1]; // 현재까지 걸린 시간 + 다음 컴퓨터 해킹까지 걸리는 시간
      if (nextTime < distance[next[0]]) {
        distance[next[0]] = nextTime;
        pq.push([next[0], nextTime]);
      }
    }
  }

  let answer = 0;
  let maxDistance = 0;
  for (let i = 0; i <= N; i++) {
    if (distance[i] !== INF) {
      answer++;
      if (maxDistance < distance[i]) {
        maxDistance = distance[i];
      }
    }
  }
  console.log(answer, maxDistance);
};

const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let idx = 1;
const T = +input[0];
for (let testCase = 0; testCase < T; testCase++) {
  const [N, D, S] = input[idx].split(" ").map(Number);
  const graph = Array.from({ length: N + 1 }, () => []); // graph[b][a] = s

  for (let i = 1; i <= D; i++) {
    const [a, b, s] = input[idx + i].split(" ").map(Number);
    graph[b].push([a, s]);
  }
  idx += D + 1;

  solution(N, graph, S);
}
