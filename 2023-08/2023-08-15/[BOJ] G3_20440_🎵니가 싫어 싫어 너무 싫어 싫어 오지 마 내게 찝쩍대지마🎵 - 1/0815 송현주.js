/*
[BOJ] 20440_니가 싫어 싫어 너무 싫어 싫어 오지 마 내게 찝쩍대지마-1_골드3
https://www.acmicpc.net/problem/20440
*/

const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const N = +input[0];
const timeMap = {};
for (let i = 1; i <= N; i++) {
  const [start, end] = input[i].split(" ").map(Number);
  timeMap[start] = timeMap[start] ? timeMap[start] + 1 : 1;
  timeMap[end] = timeMap[end] ? timeMap[end] - 1 : -1;
}

let cnt = 0;
let start = 0;
let result = [0, 0, 0];

for (let [k, v] of Object.entries(timeMap)) {
  // 시작 위치가 0으로 초기화 되어 있다면 현재 시간을 시작으로 지정
  if (start === 0) {
    start = k;
  }

  // 현재 시간에 나간 모기가 있다면 이전까지 모기 수와 시간을 저장
  if (v < 0) {
    if (result[2] < cnt) {
      result = [start, k, cnt];
    }
    start = 0; // 다음 시간을 시작으로 갱신하기 위해 초기화
    cnt += v; // 나간 모기 계산
    continue;
  }

  // 현재 시간에 추가된 모기가 있는 경우에만 갱신
  if (v > 0) {
    start = k;
    cnt += v;
  }
}

// 가장 많은 모기가 있는 시간대 출력
console.log(`${result[2]}\n${result[0]} ${result[1]}`);
