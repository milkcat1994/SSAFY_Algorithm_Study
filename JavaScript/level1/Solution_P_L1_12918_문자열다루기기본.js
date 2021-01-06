// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12915

function solution(s) {
    return /^\d{6}$|^\d{4}$/g.test(s);
}