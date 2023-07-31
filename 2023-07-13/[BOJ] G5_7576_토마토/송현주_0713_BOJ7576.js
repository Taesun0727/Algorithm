/* 
[BOJ] 7576_토마토_골드5
https://www.acmicpc.net/problem/7576
*/

const solution = (input) => {
  const [[Y, X], ...box] = input.map((item) => item.split(" ").map(Number));
  const isIn = (nx, ny) => nx >= 0 && ny >= 0 && nx < X && ny < Y;
  const isVisited = Array.from({ length: X }, () => Array.from({ length: Y }, () => false));

  const bfs = (tomatoes) => {
    const dxdy = [[-1, 0], [1, 0], [0, -1], [0, 1]]; // 상하좌우 이동
    queue = tomatoes;
    let idx = 0;
    let day = 0;
    
    while (queue.length !== idx) {
      let [curX, curY, curDay] = queue[idx];
      day = curDay;
      // 4방 탐색
      for (let i = 0; i < 4; i++) {
        let nx = curX + dxdy[i][0];
        let ny = curY + dxdy[i][1];

        if (!isIn(nx, ny) || isVisited[nx][ny]) continue;
        if (box[nx][ny] === -1) continue;

        isVisited[nx][ny] = true;
        queue.push([nx, ny, curDay + 1]);
        box[nx][ny] = 1;
      }
      idx++;
    }
    return day;
  };

  // 탐색 전 박스에 있는 익은 토마토를 확인
  let isAllTomato = true;
  const tomatoes = [];
  for (let x = 0; x < X; x++) {
    for (let y = 0; y < Y; y++) {
      if (box[x][y] !== 1) {
        isAllTomato = false;
        continue;
      }
      isVisited[x][y] = true;
      tomatoes.push([x, y, 0]);
    }
  }

  // 탐색 전 상자에 전부 익은 토마토만 있다면 0
  if (isAllTomato) return 0;

  // 탐색 후 안익은 토마토가 있다면 -1
  const answer = bfs(tomatoes);
  for (let x = 0; x < X; x++) {
    if (box[x].includes(0)) return -1;
  }

  // 탐색 후 모두 익었다면 걸린 일 수 리턴
  return answer;
};
