/*
[BOJ] 2533_사회망 서비스(SNS)_골드3
https://www.acmicpc.net/problem/2533
*/

const dfs = (v) => {
  isVisited[v] = true;

  for (let child of graph[v]) {
    if (isVisited[child]) continue;
    dfs(child);

    // 현재 노드가 얼리어덥터가 아닌 경우
    dp[v][0] += dp[child][1];

    // 현재 노드가 얼리어덥터인 경우
    dp[v][1] += Math.min(dp[child][0], dp[child][1]);
  }
};

const N = +input[0];
// dp[v][0] = 얼리어덥터가 아닌 경우
// dp[v][1] = 얼리어덥터인 경우, 1로 시작(본인 카운팅)
const dp = Array.from({ length: N + 1 }, () => [0, 1]);
const graph = Array.from({ length: N + 1 }, () => []);
const isVisited = Array(N + 1).fill(false);
for (let i = 1; i < N; i++) {
  const [from, to] = input[i].split(" ").map(Number);
  graph[from].push(to);
  graph[to].push(from);
}

dfs(1);
console.log(Math.min(dp[1][0], dp[1][1]));
