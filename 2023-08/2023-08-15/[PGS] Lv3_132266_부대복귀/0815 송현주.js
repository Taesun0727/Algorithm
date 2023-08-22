/*
[PGS] 132266_부대복귀_lv3
https://school.programmers.co.kr/learn/courses/30/lessons/132266
*/

function solution(n, roads, sources, destination) {
    const result = [];
    const graph = Array.from({length : n + 1}, () => []);

    // 다익스트라 
    const dijkstra = () => {
        
        const distance = Array(n + 1).fill(Infinity);
        const queue = [[destination, 0]];
        distance[destination] = 0;
        
        let idx = 0;
        while(queue.length && idx < queue.length) {
            let [v, time] = queue[idx];
            
            if(distance[v] < time) continue;
            
            for(let next of graph[v]) {
                
                let moveTime = time + 1;
                
                if(moveTime < distance[next]) {
                    distance[next] = moveTime;
                    queue.push([next, moveTime]);
                }
            }   
            idx++;
        }
        return distance;
    }
    
    
    // 연결 관계 저장
    for(let road of roads) {
        const[from, to] = road;
        graph[from].push(to);
        graph[to].push(from);
    }
    
    // 다익스트라 실행 - 목적지로 시작
    const distance = dijkstra();

    // 거리 저장
    for(let source of sources) {
        result.push(distance[source] === Infinity ? -1 : distance[source]);
    }
    return result;   
}
