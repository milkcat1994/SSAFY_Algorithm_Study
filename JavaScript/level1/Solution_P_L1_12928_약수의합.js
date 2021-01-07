// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12928

function solution(n) {
    var answer = 0;
    var half = Math.floor(Math.sqrt(n));
    
    for(var i=1; i<=half; i++){
        if(n % i == 0){
            answer+=i;
        }
    }
    i--;
    return (i === n/i) ? answer-i : answer;
}