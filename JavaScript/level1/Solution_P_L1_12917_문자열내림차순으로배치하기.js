// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12916

function solution(s) {
    return s.split('').sort((a, b) => a>=b ? -1 : 1).join('');
}
