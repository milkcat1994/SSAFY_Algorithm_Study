// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12935

function solution(arr) {
    var min = Math.min(...arr);
    var answer = arr.filter((x) => x != min);
    
    return !answer.length ? [-1] : answer;
}