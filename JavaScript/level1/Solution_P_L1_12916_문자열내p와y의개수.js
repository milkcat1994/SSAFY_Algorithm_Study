// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12916

function solution(s){
    return s.replace(/y/gi, '').length == s.replace(/p/gi, '').length;
}