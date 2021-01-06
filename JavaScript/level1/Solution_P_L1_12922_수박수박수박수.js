// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12922

function solution(n) {
    var arr = Array(Math.floor(n/2)).fill("수박");
    return arr.join('') + (n%2 == 0 ? "" : "수" );
}