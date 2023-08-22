/*
[BOJ] 11437_양 구출 작전_골드3
https://www.acmicpc.net/problem/16437
*/

const solution = (input) => {
  let [[N], ...map] = input.map((line) => line.split(" "));
  N = +N;
  const graph = Array.from({ length: N + 1 }, () => []);

  // DFS함수
  const dfs = (node) => {
    let cnt = 0;
    for (let next of graph[node]) {
      cnt += dfs(next);
    }
    if (node === 1) return cnt;
    if (map[node - 2][0] === "W") {
      cnt -= +map[node - 2][1];
      if (cnt < 0) cnt = 0;
    } else {
      cnt += +map[node - 2][1];
    }
    return cnt;
  };

  // 섬 정보 및 연결 여부 저장
  for (let i = 2; i <= N; i++) {
    graph[map[i - 2][2]].push(i);
  }

  // 탐색 시작
  return dfs(1);
};
