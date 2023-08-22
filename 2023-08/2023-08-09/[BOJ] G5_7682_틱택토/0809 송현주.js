/*
[BOJ] 7682_틱택토_골드5
https://www.acmicpc.net/problem/7682
*/

const isValidGame = (board, xCnt, oCnt) => {
  
  let oDoneCnt = 0;
  let xDoneCnt = 0;
  
  // 가로줄이 O인경우, 탐색
  // 가로줄 한 줄이 O인 경우를 찾았는데, xCnt가 oCnt보다 큰 경우, x가 더 게임을 진행했기 때문에 INVALID
  for (let line of board) {
    if (line[0] === "O" && line[1] === "O" && line[2] === "O") {
      oDoneCnt++;
      if (xCnt > oCnt) return false;
    }
  }

  // 세로줄이 O인 경우, 탐색
  //세로줄 한 줄이 O인 경우를 찾았는데, xCnt가 oCnt보다 큰 경우, x가 더 게임을 진행했기 때문에 INVALID
  for (let c = 0; c < 3; c++) {
    if (board[0][c] === "O" && board[1][c] === "O" && board[2][c] === "O") {
      oDoneCnt++;
      if (xCnt > oCnt) return false;
    }
  }

  // 대각선이 O인 경우
  if (board[0][0] === "O" && board[1][1] === "O" && board[2][2] === "O") {
    oDoneCnt++;
    if (xCnt > oCnt) return false;
  }
  if (board[0][2] === "O" && board[1][1] === "O" && board[2][0] === "O") {
    oDoneCnt++;
    if (xCnt > oCnt) return false;
  }

  // 가로줄이 X인 경우
  // 가로줄 한 줄이 X인 경우를 찾았는데, oCnt + 1이 xCnt와 같지 않은 경우, o가 게임을 더 진행함
  // 또는 oDoneCnt가 0이 아닌 경우, 이미 게임이 끝난 경우 임으로 INVALID
  for (let line of board) {
    if (line[0] === "X" && line[1] === "X" && line[2] === "X") {
      xDoneCnt++;
      if (!oDoneCnt && oCnt + 1 !== xCnt) return false;
    }
  }

  // 세로줄이 X인 경우, 탐색
  //세로줄 한 줄이 X인 경우를 찾았는데,  oCnt + 1이 xCnt와 같지 않은 경우, o가 게임을 더 진행함
  for (let c = 0; c < 3; c++) {
    if (board[0][c] === "X" && board[1][c] === "X" && board[2][c] === "X") {
      xDoneCnt++;
      if (!oDoneCnt && oCnt + 1 !== xCnt) return false;
    }
  }

  // 대각선이 X인 경우
  if (board[0][0] === "X" && board[1][1] === "X" && board[2][2] === "X") {
    xDoneCnt++;
    if (!oDoneCnt && oCnt + 1 !== xCnt) {
      return false;
    }
  }
  if (board[0][2] === "X" && board[1][1] === "X" && board[2][0] === "X") {
    xDoneCnt++;
    if (!oDoneCnt && oCnt + 1 !== xCnt) return false;
  }

  // 아직 끝나지 않은 게임인지
  if (!oDoneCnt && !xDoneCnt) {
    for (let line of board) {
      if (line.includes(".")) {
        return false;
      }
    }
  }
  return true;
};

// 입력
const INVALID = "invalid";
const VALID = "valid";
const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

let answer = [];
let idx = 0;

while (input.length !== idx) {
  if (input[idx] === "end") break;

  const game = input[idx].split("");
  let xCnt = 0;
  let oCnt = 0;

  const board = [];

  // 게임판 만들기 및 X와 O의 수 카운트
  while (game.length) {
    let line = game.splice(0, 3);
    for (let i = 0; i < 3; i++) {
      if (line[i] === "X") xCnt++;
      else if (line[i] === "O") oCnt++;
    }
    board.push(line);
  }

  // X와 O의 차이가 1보다 큰 경우 또는 O의 수가 x보다 많은 경우
  if (xCnt < oCnt || xCnt - oCnt > 1) {
    answer.push(INVALID);
    idx++;
    continue;
  }

  answer.push(isValidGame(board, xCnt, oCnt) ? VALID : INVALID);
  idx++;
}

console.log(answer.join("\n"));
