// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12899

function solution(n) {
    var map = [1, 2, 4];
    var answer = [];
    let quotient;
    let remains;
    while(n>0){
        n-=1;
        quotient = Math.floor(n/3);
        remains = n%3;
        answer.push(map[remains]);
        n=quotient;
    }
    return answer.reverse().join("");
}