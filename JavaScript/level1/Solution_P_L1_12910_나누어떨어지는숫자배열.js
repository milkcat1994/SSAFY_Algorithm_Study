// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12910

function solution(arr, divisor) {
    var answer = arr.filter(n => n%divisor == 0);
    
    return answer.length == 0 ? [-1] : answer.sort((a, b) => a-b);
}