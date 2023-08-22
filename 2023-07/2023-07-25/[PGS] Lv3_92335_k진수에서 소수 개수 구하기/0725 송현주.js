const isPrimeNum = (num) => {
  if (num === 2) return true;
  for (let i = 2; i < Math.sqrt(num) + 1; i++) {
    if (num % i === 0) return false;
  }
  return true;
}

const solution = (n, k) {
  let answer = 0;
  let decimals = n.toString(k).split("0");

  for (let num of decimals) {
    if (num <= 1) continue;
    if (isPrimeNum(Number(num))) answer++;
  }
  return answer;
}
