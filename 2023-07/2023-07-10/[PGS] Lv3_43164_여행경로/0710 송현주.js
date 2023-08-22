/* 
[PGS] 43164_여행경로_lv2
https://school.programmers.co.kr/learn/courses/30/lessons/43164
*/

function solution(tickets) {
  const airports = {};

  for (let ticket of tickets) {
    const [from, to] = ticket;
    airports[from] = !airports[from]
      ? [to]
      : [...airports[from], to].sort((a, b) => a.localeCompare(b));
  }

  const dfs = (currentAirport, cnt, path, airports) => {
    if (cnt === tickets.length) {
      return path;
    }

    let i = 0;
    if (!airports[currentAirport]) return;

    for (let airport of airports[currentAirport]) {
      const toAirport = airport;
      airports[currentAirport].splice(i, 1);
      path.push(toAirport);
      const result = dfs(toAirport, cnt + 1, path, airports);
      if (result) return result;

      airports[currentAirport].splice(i, 0, toAirport);
      path.pop();
      i++;
    }
  };

  const answer = dfs("ICN", 0, ["ICN"], airports);
  return answer;
}
