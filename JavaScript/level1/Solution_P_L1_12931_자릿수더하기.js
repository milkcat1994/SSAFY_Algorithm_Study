// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12931

function solution(n) {
    var answer = 0;

    while(n/10){
        answer += n%10;
        n= Math.floor(n/10);
    }
    // n.toString().split('').map((a) => answer += parseInt(a));
    
    return answer;
}