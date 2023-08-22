/*
[BOJ] 16724_피리부는 사나이_골드3
https://www.acmicpc.net/problem/16724
*/
const solution = (input) => {
  const [N, M] = input.shift().split(" ").map(Number);
  const map = input.map((line) => line.split(""));
  const isVisited = Array.from({ length: N }, () => Array(M).fill(false));
  const graph = Array.from({ length: N }, () => Array(M).fill(0));

  const dfs = (x, y, num) => {
    // 소속된 그룹이 없다면 num으로 그룹 추가
    if (graph[x][y] === 0) {
      graph[x][y] = num;
    }

    // 이미 방문했던 위치라면 종료
    if (isVisited[x][y]) return;

    // 처음 온 위치라면 탐색
    isVisited[x][y] = true;
    const [nx, ny] = moveToDir(map[x][y], x, y);

    // 현재 위치에서 갈 수 있는 위치가 있다면 그룹 변경
    if (graph[nx][ny] !== 0) {
      graph[x][y] = graph[nx][ny];
      num = graph[nx][ny];

      // 갱신된 그룹 내보내기(back)
      return num;
    }

    let result = dfs(nx, ny, num);

    // 그룹이 갱신되었다면 갱신하기
    if (result) {
      graph[x][y] = result;
      return result;
    }
  };

  let num = 1;
  let answer = 0;

  for (let x = 0; x < N; x++) {
    for (let y = 0; y < M; y++) {
      if (isVisited[x][y]) continue;
      let result = dfs(x, y, num);
      answer = Math.max(answer, result);
      if (result < num) num;
      else num++;
    }
  }
  return answer;
};

const moveToDir = (dir, x, y) => {
  switch (dir) {
    case "U":
      x--;
      break;
    case "D":
      x++;
      break;
    case "L":
      y--;
      break;
    case "R":
      y++;
      break;

    default:
      break;
  }

  return [x, y];
};
