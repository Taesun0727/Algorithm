/* 
[BOJ] 1461_도서관_골드5
https://www.acmicpc.net/problem/1461
*/

const solution = (input) => {
  const [[N, M], distances] = input.map((line) =>
    line.split(" ").map((num) => parseInt(num))
  );

  const negativeDistances = [];
  const positiveDistances = [];

  for (let distance of distances) {
    distance < 0
      ? negativeDistances.push(Math.abs(distance))
      : positiveDistances.push(Math.abs(distance));
  }

  negativeDistances.sort((a, b) => b - a);
  positiveDistances.sort((a, b) => b - a);

  let maxDistance, maxDistanceGroup;

  if (
    !negativeDistances.length ||
    negativeDistances[0] < positiveDistances[0]
  ) {
    maxDistance = positiveDistances[0];
    maxDistanceGroup = positiveDistances.splice(0, M);
  } else {
    maxDistance = negativeDistances[0];
    maxDistanceGroup = negativeDistances.splice(0, M);
  }

  let answer = 0;
  for (let i = 0; i < negativeDistances.length; i += M) {
    answer += negativeDistances[i] * 2;
  }
  for (let i = 0; i < positiveDistances.length; i += M) {
    answer += positiveDistances[i] * 2;
  }
  return answer + maxDistance;
};

// input
const input = [];
const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.on("line", (line) => {
  input.push(line);
  if (input.length == 2) {
    console.log(solution(input));
  }
});

// const input = ["1 50", "1"];
// const input = ["6 2", "3 4 5 6 11 -1"];
// const input = ["7 2", "-37 2 -6 -39 -29 11 -28"];
// const input = ["8 3", "-18 -9 -4 50 22 -26 40 -45"];
// const input = ["10 2", "0 1 2 3 4 5 6 7 8 9"];
// console.log(solution(input));
