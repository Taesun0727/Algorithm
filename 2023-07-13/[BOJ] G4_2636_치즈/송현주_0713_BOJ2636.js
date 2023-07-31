/* 
[BOJ] 2646_치즈_골드4
https://www.acmicpc.net/problem/2636
*/

const solution = (input) => {
  const dxdy = [[-1, 0], [1, 0], [0, -1], [0, 1]];
  const [[X, Y], ...plate] = input.map((line) => line.split(" ").map(Number));
  const isIn = (nx, ny) => nx >= 0 && ny >= 0 && nx < X && ny < Y;
  const countRemainingCheese = (plate) => plate.map((line) => line.filter((item) => item === 1).length).reduce((prev, cur) => prev + cur);

  // 탐색 시작
  let time = 0;
  let remainingCheese = countRemainingCheese(plate);
  let cheese = 0;

  // 녹일 치즈가 있는 동안 진행
  while (remainingCheese !== 0) {
    cheese = remainingCheese;
    const isVisited = Array.from({ length: X }, () => Array.from({ length: Y }, () => false));

    const dfs = (x, y) => {
      isVisited[x][y] = true;
      for (let i = 0; i < 4; i++) {
        let nx = x + dxdy[i][0];
        let ny = y + dxdy[i][1];

        if (!isIn(nx, ny) || isVisited[nx][ny]) continue;

        // 공기라면
        if (plate[nx][ny] === 0) {
          dfs(nx, ny);
        }
        // 치즈라면
        if (plate[nx][ny] === 1) {
          plate[nx][ny] = 0;
          isVisited[nx][ny] = true;
          remainingCheese--;
        }
      }
    };

    // 탐색 후, 시간 추가
    dfs(0, 0);
    time++;
  }

  return time + "\n" + cheese;
};
