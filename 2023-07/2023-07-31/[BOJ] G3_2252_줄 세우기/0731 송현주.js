/*
[BOJ] 2252_줄세우기_골드3
https://www.acmicpc.net/problem/2252
*/

/* DFS */
const solution = (input) => {
  const [[N, M], ...orders] = input.map((line) => line.split(" ").map(Number));
  const graph = Array.from({ length: N + 1 }, () => []);
  const isVisited = Array(N + 1).fill(false);
  const answer = [];

  const dfs = (target) => {
    isVisited[target] = true;
    
    for (let next of graph[target]) {
      if (isVisited[next]) continue;
      dfs(next);
    }
    
    answer.push(target);
  };

  // 연결 그래프
  for (let order of orders) {
    const [A, B] = order;
    graph[A].push(B);
  }

  // 탐색
  for (let student = 1; student <= N; student++) {
    if (isVisited[student]) continue;
    dfs(student);
  }
  return answer.reverse().join(" ");
};

/* 위상 정렬 */
const solution = (input) => {
  const [[N, _], ...orders] = input.map((line) => line.split(" ").map(Number));
  const graph = Array.from({ length: N + 1 }, () => []);
  const indegree = Array(N + 1).fill(0);
  const queue = [];
  const answer = [];

  for (let order of orders) {
    const [A, B] = order;
    graph[A].push(B);
    indegree[B]++;
  }

  for (let i = 1; i <= N; i++) {
    if (!indegree[i]) queue.push(i);
  }

  while (queue.length > 0) {
    const target = queue.pop();
    answer.push(target);
      
    for (let v of graph[target]) {
      indegree[v]--;
      if (!indegree[v]) queue.push(v);
    }
  }
  return answer.join(" ");
};
