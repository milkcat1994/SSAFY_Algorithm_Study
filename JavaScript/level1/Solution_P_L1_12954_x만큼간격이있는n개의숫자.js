// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12954

function solution(x, n) {
    return Array(n).fill(x).map((num, idx) => num*(idx+1));
}