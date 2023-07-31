/* 
[BOJ] 2146_다리 만들기_골드3
https://www.acmicpc.net/problem/2146
*/

const solution = (input) => {
  const dxdy = [[-1, 0], [1, 0], [0, -1], [0, 1]];
  const N = parseInt(input[0]);
  const map = input.splice(1).map((line) => line.split(" ").map((num) => (num === "1" ? -1 : 0)));

  // 범위 확인을 위한 함수 
  const isIn = (nx, ny) => 0 <= nx && 0 <= ny && nx < N && ny < N;

  // 섬의 이름 설정을 위한 함수
  const classifyIsland = (x, y, num) => {
    const queue = [];
    map[x][y] = num;
    isVisited[x][y] = true;
    queue.push([x, y]);

    while (queue.length > 0) {
      const [x, y] = queue.shift();

      for (let dir of dxdy) {
        let nx = x + dir[0];
        let ny = y + dir[1];

        if (!isIn(nx, ny) || isVisited[nx][ny]) continue;

        // 현재 탐색한 위치가 섬의 일부라면 
        if (map[nx][ny] === -1) {
          map[nx][ny] = num;
          isVisited[nx][ny] = true;
          queue.push([nx, ny]);
        }
      }
    }
  };

  // 가장 짧은 다리 찾는 함수
  const bfs = (x, y) => {
    const queue = [];
    queue.push([x, y, 0]);
    isVisited[x][y] = true;
    const islandName = map[x][y];

    let idx = 0;
    while (queue.length > 0 && idx !== queue.length) {
      const [x, y, cnt] = queue[idx];

      for (let dir of dxdy) {
        let nx = x + dir[0];
        let ny = y + dir[1];

        // 서로 다른 섬을 찾았을 경우
        if (!isIn(nx, ny) || isVisited[nx][ny]) continue;
        if (map[nx][ny] !== 0 && islandName !== map[nx][ny]) return cnt;

        // 탐색 지점이 바다라면 다리 연결 할 수 있음으로 카운트 증가
        isVisited[nx][ny] = true;
        if (map[nx][ny] === 0) {
          queue.push([nx, ny, cnt + 1]);
        }
      }
      idx++;
    }
    return Number.MAX_SAFE_INTEGER;
  };

  // 탐색 시작
  // 섬 이름 설정 
  let num = 1;
  let isVisited = Array.from({ length: N }, () => Array(N).fill(false));
  for (let x = 0; x < N; x++) {
    for (let y = 0; y < N; y++) {
      if (!map[x][y] || isVisited[x][y]) continue;
      classifyIsland(x, y, num);
      num++;
    }
  }

  // 가장 짧은 다리 탐색
  let answer = Number.MAX_SAFE_INTEGER;
  for (let x = 0; x < N; x++) {
    for (let y = 0; y < N; y++) {
      if (!map[x][y]) continue;
      isVisited = Array.from({ length: N }, () => Array(N).fill(false));
      answer = Math.min(bfs(x, y), answer);
    }
  }
  return answer;
};
