/*
[BOJ] 3584_가장 가까운 공통 조상_골드4
https://www.acmicpc.net/problem/3584
*/

const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const T = +input.shift();
const answer = [];

for (let tc = 1; tc <= T; tc++) {
  const N = +input[0];
  const graph = Array.from({ length: N + 1 }, () => ({
    parent: null,
    children: [],
  }));

  // 그래프 생성
  for (let i = 1; i < N; i++) {
    const [parent, child] = input[i].split(" ").map(Number);
    graph[parent].children.push(child);
    graph[child].parent = parent;
  }

  // 탐색 시작
  const targets = input[N].split(" ").map(Number);
  input.splice(0, N + 1);

  let current = targets[0];
  const ancestors = [current];

  while (true) {
    let p = graph[current].parent;
    if (p === null) {
      break;
    }
    ancestors.push(p);
    current = p;
  }

  current = targets[1];
  if (ancestors.includes(current)) {
    answer.push(current);
    continue;
  }

  while (true) {
    let p = graph[current].parent;
    if (ancestors.includes(p)) {
      answer.push(p);
      break;
    }
    current = p;
  }
}

console.log(answer.join("\n"));
