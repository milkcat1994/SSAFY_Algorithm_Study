// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12903

function solution(s) {
    var answer = s;
    var length = s.length;
    var startIndex = Math.ceil(length/2)-1;
    var endIndex = length%2 == 1 ? startIndex+1 : startIndex+2;
    return answer.substring(startIndex, endIndex);
}