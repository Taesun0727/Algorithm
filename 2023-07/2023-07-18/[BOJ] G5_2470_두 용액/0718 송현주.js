/* 
[BOJ] 2470_두 용액_골드5
https://www.acmicpc.net/problem/2470
*/

const solution = (input) => {
  let [[N], [...liquid]] = input.map((line) => line.split(" ").map((num) => parseInt(num)));
  let tmp = Number.MAX_SAFE_INTEGER;
  let answer = [];
  let left = 0;
  let right = N - 1;
  
  liquid.sort((a, b) => a - b);

  while (left < right) {
    
    const sum = liquid[left] + liquid[right];

    if (Math.abs(sum) < tmp) {
      tmp = Math.abs(sum);
      answer = [liquid[left], liquid[right]];
      if (tmp === 0) break;
    }
    sum < 0 ? left++ : right--;
  }
  return answer.join(" ");
};
