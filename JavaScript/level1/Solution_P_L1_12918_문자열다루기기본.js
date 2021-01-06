// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12915

function solution(s) {
    if(s.length != 4 && s.length != 6)
        return false;
    return !/[^0-9]/g.test(s);
}