/*
[PGS] 67258_보석 쇼핑_lv3
https://school.programmers.co.kr/learn/courses/30/lessons/67258
*/

function solution(gems) {
    const range = [1, gems.length];
    const kinds = new Set(gems).size;
    const pickedGems = new Map();
    
    pickedGems.set(gems[0], 1);

    const length = gems.length;
    let left = 0;
    let right = 0;
    
    while(right < length) {

        // 보석 종류만큼 보석을 담았다면 범위 확인 및 맨 처음 담음 보석 제거
        if(pickedGems.size === kinds) {
            if(right - left < range[1] - range[0]) {
                range[0] = left + 1;
                range[1] = right + 1;
            }
            
            let cnt = pickedGems.get(gems[left]) - 1;
            cnt === 0 
            ? pickedGems.delete(gems[left])
            :pickedGems.set(gems[left], cnt);
            left += 1;
        }else {
            right += 1;
            let cnt = pickedGems.get(gems[right]) ?? 0;
            pickedGems.set(gems[right], cnt + 1);
        }
    }
    return range;
}
