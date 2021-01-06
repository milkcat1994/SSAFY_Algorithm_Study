// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12915

function solution(strings, n) {
    var answer = strings;
    return answer.sort((a, b) => a[n] == b[n] ? (a > b ? 1 : -1) : ((a[n] > b[n]) ? 1 : -1));
}