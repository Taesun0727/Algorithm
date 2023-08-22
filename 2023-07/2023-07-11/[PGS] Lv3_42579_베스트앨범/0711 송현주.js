/* 
[PGS] 42579_베스트앨범_lv3
https://school.programmers.co.kr/learn/courses/30/lessons/42579
*/

function solution(genres, plays) {
  const answer = [];
  let musicMap = new Map();
  let genreSet = new Set(genres); // 장르가 총 몇 종류인지 저장

  for (let i = 0; i < genres.length; i++) {
    if (musicMap.get(genres[i]) !== undefined) {
      let tmp = [...musicMap.get(genres[i]), [i, plays[i]]];
      tmp[0] += plays[i];
      musicMap.set(genres[i], tmp);
    } else {
      musicMap.set(genres[i], [plays[i], [i, plays[i]]]);
    }
  }

  // 조건 1. 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
  const musicToArr = [...musicMap];
  musicToArr.sort((a, b) => {
    const totalA = a[1][0];
    const totalB = b[1][0];
    return totalB - totalA;
  });

  // 조건 2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
  // 조건 3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
  musicMap = new Map(musicToArr);
  for (let genre of genreSet) {
    musicMap.get(genre).sort((a, b) => {
      if (b[1] === a[1]) return a[0] - b[0];
      return b[1] - a[1];
    });
  }

  // 출력
  for (const [, v] of musicMap.entries()) {
    let loop = v.length <= 2 ? v.length - 1 : 2;
    for (let i = 1; i <= loop; i++) {
      answer.push(v[i][0]);
    }
  }
  return answer;
}
