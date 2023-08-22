/* 
[BOJ] 1107_리모컨_골드5
https://www.acmicpc.net/problem/1107
*/

const solution = (N, M, broken) => {
  let answer = Math.abs(N - 100);

  for (let channel = 0; channel < 1000000; channel++) {
    let current = channel.toString();
    let isBroken = false;

    for (let n = 0; n < current.length; n++) {
      if (broken.includes(current[n])) {
        isBroken = true;
        break;
      }
    }

    if (!isBroken) {
      answer = Math.min(answer, Math.abs(N - channel) + current.length);
    }
  }

  return answer;
};
