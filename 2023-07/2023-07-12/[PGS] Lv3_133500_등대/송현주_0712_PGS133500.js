/* 
[PGS] 133500_등대_lv3
https://school.programmers.co.kr/learn/courses/30/lessons/133500
*/

function solution(n, lighthouse) {
  let cnt = n;
  const isOnLight = Array(n + 1).fill(false);
  const graph = Array.from({ length: n + 1 }, () => new Array());
  for (let [from, to] of lighthouse) {
    graph[from].push(to);
    graph[to].push(from);
  }

  // 탐색할 등대가 있을 때까지 반복
  while (cnt > 1) {
    // 1부터 n번째 등대까지 반복
    for (let i = 1; i <= n; i++) {
      // 이어져 있는 등대가 1인 경우 찾아서 제거
      if (graph[i].length === 1) {
        const [target] = graph[i];
        graph[i] = [];
        // 현재 탐색하는 등대에 불이 꺼져있다면 부모 등대의 불 켜주기
        if (!isOnLight[i]) {
          isOnLight[target] = true;
        }
        graph[target].splice(graph[target].indexOf(i), 1);
        cnt--;
      }
    }
  }
  return isOnLight.filter((isOn) => isOn).length || 0;
}
