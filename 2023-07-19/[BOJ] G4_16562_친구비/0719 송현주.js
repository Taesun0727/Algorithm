/* 
[BOJ] 16562_친구비_골드4
https://www.acmicpc.net/problem/16562
*/
const solution = (input) => {
  let result = 0;
  const [[N, M, K], moneyTable, ...rest] = input.map((line) => line.split(" ").map(Number));
  const isAlreadyFriend = Array(N + 1).fill(false);
  
  // dfs 탐색
  const dfs = (currentFriend, need) => {
    let money = need;
    for (let friend of relationship[currentFriend]) {
      if (isAlreadyFriend[friend]) continue;
      isAlreadyFriend[friend] = true;
      money = dfs(friend, Math.min(money, moneyTable[friend - 1]));
    }
    return Math.min(need, money);
  };

  // 친구 관계 연결
  const relationship = Array.from({ length: N + 1 }, () => []);
  for (let relation of rest) {
    const [from, to] = relation;
    relationship[from].push(to);
    relationship[to].push(from);
  }

  // 친구비 계산
  for (let i = 1; i <= N; i++) {
    if (isAlreadyFriend[i]) continue;
    isAlreadyFriend[i] = true;
    result += dfs(i, moneyTable[i - 1]);
  }
  return result <= K ? result : "Oh no";
};
