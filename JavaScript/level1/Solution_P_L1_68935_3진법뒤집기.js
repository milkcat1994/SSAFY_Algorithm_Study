// 출처 : https://programmers.co.kr/learn/courses/30/lessons/68935

function solution(n) {
    var answer = 0;
    let startPow = Math.floor(baseLog(n,3));
    
    let origin = n;
    let tmp;
    let arr=[];

    for(let i=startPow; i>=0; i--){
        tmp = Math.floor(origin/(3**i));
        arr.push(tmp);
        origin -= (3**i)*tmp;
    }
    
    for(let i=0; i<=startPow; i++){
        tmp = (3**i) * arr[i];
        answer+=tmp;
    }
    return answer;
}

function baseLog(x, base){
    return Math.log10(x)/Math.log10(base);
}