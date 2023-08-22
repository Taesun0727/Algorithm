/*
[PGS] 161988_연속 펄스 부분 수열의 합
https://school.programmers.co.kr/learn/courses/30/lessons/161988
*/

function solution(sequence) {
    let answer = 0;
    let positive = sequence[0];
    let negative = -sequence[0];
    
    if(sequence.length === 1) return Math.max(positive, negative);
    
    for(let idx=1; idx<sequence.length; idx++) {
        let next = sequence[idx];
        positive = idx % 2 === 0 ? Math.max(positive + next, next) : Math.max(positive - next, -next); 
        negative = idx % 2 === 0 ? Math.max(negative - next, -next) : Math.max(negative + next, next);  
        
        let maxSum = Math.max(positive, negative);
        answer = Math.max(answer, maxSum);
    }
    return answer;
}
