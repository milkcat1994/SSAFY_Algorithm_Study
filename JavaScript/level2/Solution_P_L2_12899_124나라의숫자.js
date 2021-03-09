// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12899

function solution(n) {
    var map = {1:1, 2:2, 3:4};
    var answer = [];
    let quotient;
    let remains;
    while(n>3){
        quotient = Math.floor(n/3);
        remains = n%3;
        if(remains==0){
            quotient-=1;
            remains = 3;
        }
        answer.push(map[remains]);
        n=quotient;
    }
    answer.push(map[n]);
    return answer.reverse().join("");
}