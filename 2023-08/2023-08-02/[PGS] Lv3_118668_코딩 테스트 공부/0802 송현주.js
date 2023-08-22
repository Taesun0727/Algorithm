/*
[PGS] 118668_코딩테스트공부_lv3
https://school.programmers.co.kr/learn/courses/30/lessons/118668
*/

// 목표 코딩력과 알고력 계산을 위함 함수
const findGoalScore = (problems) => {
  const goal = Array(2).fill(0);
  for (let problem of problems) {
    const [needAlp, needCop] = problem;
    goal[0] = Math.max(goal[0], +needAlp);
    goal[1] = Math.max(goal[1], +needCop);
  }
  return goal;
};

const solution = (alp, cop, problems) => {

  // 최종 목표 알고력과 코딩력 찾기
  const goal = findGoalScore(problems);
  if (goal[0] <= alp && goal[1] <= cop) return 0;
  if (goal[0] <= alp) alp = goal[0];
  if (goal[1] <= cop) cop = goal[1];

  // 목표 알고력과 코딩력만큼의 dp생성 *초기값은 가장 큰 값(가장 작은 값을 찾아야 함으로!)
  // dp[a][c] = k 알고력 a와 코딩력 c인 문제를 풀기 위해 k시간이 걸림
  const dp = Array.from({ length: goal[0] + 2 }, () =>
    Array(goal[1] + 2).fill(Number.MAX_SAFE_INTEGER)
  );

  // 현재 알고력과 코딩력으로 시작
  dp[alp][cop] = 0;

  // 문제 풀기 시작
  for (let a = alp; a < dp.length - 1; a++) {
    for (let c = cop; c < dp[0].length - 1; c++) {
      // 알고력 공부해서 키우기
      dp[a + 1][c] = Math.min(dp[a + 1][c], dp[a][c] + 1);

      // 코딩력 공부해서 키우기
      dp[a][c + 1] = Math.min(dp[a][c + 1], dp[a][c] + 1);

      // 문제 풀어서 실력 키우기
      for (const [alp_req, cop_req, alp_rwd, cop_rwd, cost] of problems) {
        
        // 풀 수 없는 문제면 생략(현재 알고력 또는 코딩력보다 요구하는 알고력 또는 코딩력이 더 크다면 문제 못품)
        if (a < alp_req || c < cop_req) continue;

        // 알고력과 코딩력 모두 초과했을 경우
        if (goal[0] < a + alp_rwd && goal[1] < c + cop_rwd) {
          dp[goal[0]][goal[1]] = Math.min(dp[goal[0]][goal[1]], dp[a][c] + cost);
          continue;
        }

        // 알고력만 초과했을 경우
        if (goal[0] < a + alp_rwd) {
          dp[goal[0]][c + cop_rwd] = Math.min(dp[goal[0]][c + cop_rwd], dp[a][c] + cost);
          continue;
        }

        // 코딩력만 초과했을 경우
        if (goal[1] < c + cop_rwd) {
          dp[a + alp_rwd][goal[1]] = Math.min(dp[a + alp_rwd][goal[1]], dp[a][c] + cost);
          continue;
        }

        // 해당 문제를 풀이해서 얻은 알고력과 코딩력을 쌓기 위해 걸리는 시간 중 최소 값
        dp[a + alp_rwd][c + cop_rwd] = Math.min(dp[a + alp_rwd][c + cop_rwd], dp[a][c] + cost);
      }
    }
  }

  return dp[goal[0]][goal[1]];
};
