/* 
[BOJ] 1092_배_골드5
https://www.acmicpc.net/problem/1092
*/
const solution = (N, cranes, M, boxes) => {
  // 내림차순 정렬
  cranes.sort((a, b) => b - a);
  boxes.sort((a, b) => b - a);

  // 가장 큰 무게를 옮기는 크레인 < 옮길 박스라면 모든 박스를 옮길 수 없으므로 -1 리턴
  if (cranes[0] < boxes[0]) return -1;

  const workedCnt = new Array(N).fill(ㄴ0);

  let workingTime = 0;
  // 옮길 박스가 없을 때까지 반복
  while (boxes.length > 0) {
    for (let crane of cranes) {
      for (let idx = 0; idx < boxes.length; idx++) {
        if (crane < boxes[idx]) continue;
        boxes.splice(idx, 1);
        break;
      }
    }
    workingTime++;
  }
  return workedCnt[0];
};
