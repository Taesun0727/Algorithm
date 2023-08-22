/* 
[BOJ] 1520_내리막길_골드3
https://www.acmicpc.net/problem/1520
*/

const solution = (input) => {
  const dxdy = [[0, -1], [0, 1], [-1, 0], [1, 0]];
  const [[M, N], ...map] = input.map((line) =>
    line.split(" ").map((num) => parseInt(num))
  );
  const isVisited = Array.from({ length: M }, () => Array(N).fill(false));
  const dp = Array.from({ length: M }, () => Array(N).fill(-1));
  const isIn = (nx, ny) => nx >= 0 && ny >= 0 && nx < M && ny < N;
  const dfs = (x, y) => {
    
    // 가능한 경로 수 추가
    if (x === M - 1 && y === N - 1) {
      return 1;
    }

    // 이미 경로 수가 존재하면
    if (isVisited[x][y]) {
      return dp[x][y];
    }

    let cnt = 0;
    for (let i = 0; i < 4; i++) {
      let nx = x + dxdy[i][0];
      let ny = y + dxdy[i][1];

      if (!isIn(nx, ny)) continue;
      if (map[nx][ny] < map[x][y]) {
        cnt += dfs(nx, ny);
      }
    }

    dp[x][y] = cnt;
    isVisited[x][y] = true;
    return cnt;
  };

  dfs(0, 0);
  return dp[0][0];
};
