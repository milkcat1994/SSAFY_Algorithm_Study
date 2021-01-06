// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12921

function solution(n) {
    var answer = 0;
    var arr = Array(n+1).fill(true);
    arr[0] = arr[1] = false;

    var idx;
    for(var i = 2; i<n; i++){
        idx = i;
        for(var j = 0; j<n; j++){
            idx+=i;
            if(n < idx) break;
            arr[idx] = false;
        }
    }
    
    arr.map((num) => num ? answer++ : answer+=0);
    return answer;
}