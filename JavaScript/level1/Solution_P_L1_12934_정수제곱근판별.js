// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12934

function solution(n) {
    var answer = Math.sqrt(n);
    if(answer == Math.floor(answer)){
        return Math.pow(answer+1,2);
    }
    return -1;
}