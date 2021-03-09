// 출처 : https://programmers.co.kr/learn/courses/30/lessons/70128

function solution(a, b) {
    var answer=0;
    a.map((curValue, curIdx) => answer+=curValue*b[curIdx]);
    return answer;
}