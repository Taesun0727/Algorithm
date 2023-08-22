/* 
[BOJ] 2580_스도쿠_골드4
https://www.acmicpc.net/problem/2580
*/
const solution = (input) => {
  const sudoku = input.map((line) => line.split(" ").map(Number));
  const zeroPositions = [];

  // 빈 칸 위치 찾기
  const findZeroPosition = () => {
    for (let x = 0; x < 9; x++) {
      for (let y = 0; y < 9; y++) {
        if (sudoku[x][y] !== 0) continue;
        zeroPositions.push([x, y]);
      }
    }
  };

  // 해당 위치에 들어갈 수 찾기
  const findNumber = (x, y, num) => {
    // 가로, 세로 탐색
    for (let i = 0; i < 9; i++) {
      if (sudoku[x][i] === num) return false;
      if (sudoku[i][y] === num) return false;
    }

    // 3*3 탐색
    let rangeX = Math.floor(x / 3) * 3;
    let rangeY = Math.floor(y / 3) * 3;
    for (let i = rangeX; i < rangeX + 3; i++) {
      for (let j = rangeY; j < rangeY + 3; j++) {
        if (sudoku[i][j] === num) return false;
      }
    }
    return true;
  };

  // dfs 함수
  const dfs = (cnt) => {
    if (cnt === zeroPositions.length) {
      for (let x = 0; x < 9; x++) {
        console.log(sudoku[x].join(" "));
      }
      process.exit();
    }

    const [x, y] = zeroPositions[cnt];
    for (let i = 1; i <= 9; i++) {
      if (findNumber(x, y, i)) {
        sudoku[x][y] = i;
        dfs(cnt + 1);
      }
      // 백트래킹
      sudoku[x][y] = 0;
    }
  };

  // 0의 위치를 찾아서 모두 저장
  findZeroPosition();

  // 탐색
  dfs(0);
};
