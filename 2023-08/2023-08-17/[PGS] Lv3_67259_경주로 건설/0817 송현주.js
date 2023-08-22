/*
[BOJ] 67259_경주로 건설_lv3
https://school.programmers.co.kr/learn/courses/30/lessons/67259
*/

// road 클래스 (좌표, 비용, 방향) 
class Road {
  constructor(x, y, cost, dir) {
    this.x = x;
    this.y = y;
    this.cost = cost;
    this.dir = dir;
  }
}

function solution(board) {
  const N = board.length;
  const costs = Array.from({ length: N }, () => Array(N).fill(Infinity));
  const dirs = [[1, 0], [0, -1], [-1, 0], [0, 1]]; // 상 - 하 - 좌 - 우

  // 범위 확인 및 코너 여부 확인
  const isIn = (nx, ny) => 0 <= nx && 0 <= ny && nx < N && ny < N;
  const isCorner = (prevDir, curDir) => prevDir !== -1 && prevDir != curDir;

  const bfs = () => {
    const queue = [];
    let index = 0;
    queue.push(new Road(0, 0, 0, -1));

    while (queue.length && index < queue.length) {
      const road = queue[index++];

      // 현재 거리까지 비용이 최종 비용보다 크다면 탐색 x
      if(costs[N - 1][N - 1] < road.cost) continue;

      // 4방탐색
      for (let idx = 0; idx < 4; idx++) {
        let nx = road.x + dirs[idx][0];
        let ny = road.y + dirs[idx][1];

        // 현재 방향에서 반대 방향이거나 범위 벗어났거나 벽이라면 탐색 x
        if (road.dir !== -1 && Math.abs(idx - road.dir) === 2) continue;
        if (!isIn(nx, ny) || board[nx][ny] === 1) continue;

        // 비용 계산 + 코너라면 비용 추가 
        let nextCost = 100 + road.cost;
        if (isCorner(road.dir, idx)) nextCost += 500;

        // 계산된 비용이 저장된 비용보다 적으면 갱신
        if (nextCost <= costs[nx][ny]) {
          costs[nx][ny] = nextCost;
          queue.push(new Road(nx, ny, nextCost, idx));
        } else if (nextCost <= costs[nx][ny] + 500) {
          queue.push(new Road(nx, ny, nextCost, idx));
        }
      }
    }
  };

  // 탐색 시작
  bfs();

  // 최종 비용 출력
  return costs[N - 1][N - 1];
}
