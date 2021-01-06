// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12922

function solution(n) {
    return "수박".repeat(Math.ceil(n/2)).slice(0, n);
}