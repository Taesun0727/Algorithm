/* 
[PGS] 42884_단속 카메라_lv3
https://school.programmers.co.kr/learn/courses/30/lessons/42884
*/
function solution(routes) {
    routes.sort((a,b) => a[1] - b[1]);
    let cameraLocation = routes[0][1]; // 첫번째 차량이 나간 지점(카메라 설치)
    let answer = 1; // 카메라 한 대 설치
    
    for(let i=1; i<routes.length; i++) {
        
        if(routes[i][0] <= cameraLocation) continue;
        
        // 카메라 밖에서 다음 차량이 진입한 경우, 카메라 설치
        cameraLocation = routes[i][1];
        answer ++;
    }
    
    return answer;
}
