/*
[PGS] 72413_합승택시요금_lv3
https://school.programmers.co.kr/learn/courses/30/lessons/72413
*/

// n : 지점의 수
// s : 시작 위치
// a : a의 도착지점
// b : b의 도착지점
// fares : 요금

function solution(n, s, a, b, fares) {
  let answer = Number.MAX_SAFE_INTEGER;
  const distance = Array.from({ length: n + 1 }, () =>
    Array(n + 1).fill(Infinity)
  );

  const floyd = () => {
    for (let k = 1; k <= n; k++) {
      for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= n; j++) {
          if (distance[i][k] + distance[k][j] < distance[i][j]) {
            distance[i][j] = distance[i][k] + distance[k][j];
          }
        }
      }
    }
  };

  // 이동 거리별 요금 테이블
  for (let fare of fares) {
    const [from, to, cost] = fare;
    distance[from][to] = cost;
    distance[to][from] = cost;
  }

  // 초기화
  for (let idx = 1; idx <= n; idx++) distance[idx][idx] = 0;

  // 플로이드 워셜 함수 실행
  floyd();

  for (let line of distance) {
    let sum = line[s] + line[a] + line[b];
    answer = Math.min(answer, sum);
  }

  return answer;
}
