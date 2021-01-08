// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12933

function solution(n) {
    var arr = [];
    while(n/10){
        arr.push(n%10);
        n = Math.floor(n/10);
    }
    arr.sort((a, b) => b-a);
    return parseInt(arr.join(''));
}